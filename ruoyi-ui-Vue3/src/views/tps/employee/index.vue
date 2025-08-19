<template>
  <div class="app-container">
    <!-- Task Table -->
    <el-table :data="tasks" v-loading="loading" style="width: 100%">
      <el-table-column prop="title" label="任务标题" width="180"></el-table-column>
      <el-table-column prop="description" label="描述" show-overflow-tooltip></el-table-column>
      <el-table-column prop="deadline" label="截止日期" width="120"></el-table-column>
      <el-table-column prop="priority" label="优先级" width="100"></el-table-column>
      <el-table-column prop="status" label="任务状态" width="120"></el-table-column>
      <el-table-column label="操作" width="120" align="center">
        <template #default="scope">
          <el-button link type="primary" @click="handleDetails(scope.row)">详情</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- Details Dialog -->
    <el-dialog title="任务详情" v-model="detailsVisible" width="800px" append-to-body>
      <div v-if="selectedTask" class="details-container">
        <el-descriptions :column="2" border>
          <el-descriptions-item label="任务标题">{{ selectedTask.title }}</el-descriptions-item>
          <el-descriptions-item label="当前状态">
            <el-tag>{{ selectedTask.status }}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="优先级">{{ selectedTask.priority }}</el-descriptions-item>
          <el-descriptions-item label="截止日期">{{ selectedTask.deadline }}</el-descriptions-item>
          <el-descriptions-item label="负责人">（暂无数据）</el-descriptions-item>
          <el-descriptions-item label="是否需确认">{{ selectedTask.needConfirm ? '是' : '否' }}</el-descriptions-item>
          <el-descriptions-item label="任务描述" :span="2">{{ selectedTask.description }}</el-descriptions-item>
          <el-descriptions-item label="附件列表" :span="2">
            <div v-if="selectedTask.haveAttachment && selectedTask.attachmentUrl.length > 0">
              <p v-for="(url, index) in selectedTask.attachmentUrl" :key="index" style="margin: 0;">
                <a @click.prevent="downloadFile(url)" href="#" class="attachment-link">{{ getFileName(url) }}</a>
              </p>
            </div>
            <div v-else>无</div>
          </el-descriptions-item>
        </el-descriptions>

        <el-divider content-position="left">评论区</el-divider>
        <div class="placeholder-area">（暂无数据）</div>

        <el-divider content-position="left">流转记录</el-divider>
        <div class="placeholder-area">（暂无数据）</div>
      </div>

      <template #footer>
        <div class="dialog-footer">
          <el-button @click="handleDownload(selectedTask)" v-if="selectedTask && selectedTask.haveAttachment">下载附件</el-button>
          <el-button type="danger" @click="handleFeedback(selectedTask)">反馈</el-button>
          <el-button type="warning" @click="handleSplit(selectedTask)" v-if="selectedTask && selectedTask.recipientType === '支行管理层'">拆分</el-button>
          <el-button @click="detailsVisible = false">关闭</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- Feedback Dialog -->
    <el-dialog title="任务反馈" v-model="feedbackFormVisible" width="800px" append-to-body>
      <el-form ref="feedbackFormRef" :model="feedbackForm" :rules="feedbackRules" label-width="80px">
        <el-form-item label="反馈状态" prop="status">
          <el-select v-model="feedbackForm.status" placeholder="请选择状态">
            <el-option label="待确认" value="待确认" v-if="feedbackForm.needConfirm"></el-option>
            <el-option label="已完成" value="已完成" v-if="!feedbackForm.needConfirm"></el-option>
            <el-option label="未完成" value="未完成"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label="feedbackForm.status === '未完成' ? '原因' : '评论'" prop="comment">
          <el-input v-model="feedbackForm.comment" type="textarea" placeholder="请输入内容"></el-input>
        </el-form-item>
        <el-form-item label="上传附件">
          <FileUpload v-model="feedbackForm.attachments" :file-size="50" :file-type="['doc', 'docx', 'png', 'jpg']"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="cancelFeedback">取 消</el-button>
          <el-button type="primary" @click="submitFeedbackForm">确 定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, getCurrentInstance } from 'vue';
import { listEmployeeTasks, submitFeedback } from "@/api/tps/employee";
import useUserStore from '@/store/modules/user';
import FileUpload from '@/components/FileUpload/index.vue';

const { proxy } = getCurrentInstance();
const userStore = useUserStore();

// --- 响应式状态定义 ---

// 主表格状态
const tasks = ref([]);
const loading = ref(true);

// 详情对话框状态
const detailsVisible = ref(false);
const selectedTask = ref(null);

// 反馈对话框状态
const feedbackFormVisible = ref(false);
const feedbackFormRef = ref(null);
const feedbackForm = reactive({
  taskId: null,
  status: '',
  comment: '',
  createBy: null,
  attachments: '',
  needConfirm: null
});
const feedbackRules = reactive({
  status: [{ required: true, message: "反馈状态不能为空", trigger: "change" }],
  comment: [{ required: true, message: "评论或原因不能为空", trigger: "blur" }]
});

/**
 * 打开详情对话框。
 * 未来可在此处调用API获取更完整的任务详情。
 */
function handleDetails(row) {
  selectedTask.value = row;
  detailsVisible.value = true;
}

/**
 * 获取任务列表。
 */
function getTaskList() {
  loading.value = true;
  listEmployeeTasks().then(response => {
    tasks.value = response.data;
    loading.value = false;
  });
}

/**
 * 打开反馈对话框。
 */
function handleFeedback(row) {
  // 确保任务对象有效
  if (!row || !row.taskId) {
    proxy.$modal.msgError("无法获取任务信息");
    return;
  }
  detailsVisible.value = false; // 关闭详情对话框
  feedbackForm.taskId = row.taskId;
  feedbackForm.needConfirm = row.needConfirm;
  if (feedbackFormRef.value) {
    feedbackFormRef.value.resetFields();
  }
  feedbackFormVisible.value = true;
}

/**
 * 提交反馈表单。
 */
function submitFeedbackForm() {
  feedbackFormRef.value.validate(valid => {
    if (valid) {
      feedbackForm.createBy = userStore.id; // 添加创建人ID
      submitFeedback(feedbackForm).then(() => {
        proxy.$modal.msgSuccess("反馈提交成功");
        feedbackFormVisible.value = false;
        getTaskList(); // 刷新列表
      });
    }
  });
}

/**
 * 取消反馈。
 */
function cancelFeedback() {
  feedbackFormVisible.value = false;
}

/**
 * 处理“拆分”操作。
 */
function handleSplit(row) {
  console.log("拆分任务:", row);
  proxy.$modal.msgSuccess("拆分任务信息已打印到控制台");
}

/**
 * 下载任务的所有附件。
 */
function handleDownload(row) {
  if (row && row.attachmentUrl && Array.isArray(row.attachmentUrl) && row.attachmentUrl.length > 0) {
    row.attachmentUrl.forEach(url => downloadFile(url));
  } else {
    proxy.$modal.msgError("没有可下载的附件");
  }
}

// --- 辅助函数 ---

/**
 * 从URL中提取文件名。
 */
function getFileName(url) {
  if (!url) return '';
  return url.substring(url.lastIndexOf('/') + 1);
}

/**
 * 为单个文件URL触发下载。
 */
function downloadFile(url) {
  const link = document.createElement('a');
  link.href = import.meta.env.VITE_APP_BASE_API + url;
  link.style.display = 'none';
  link.download = getFileName(url);
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

// --- 生命周期钩子 ---
onMounted(() => {
  getTaskList();
});

</script>

<style scoped>
.app-container {
  padding: 20px;
}
.details-container .el-descriptions {
  margin-bottom: 20px;
}
.placeholder-area {
  padding: 20px;
  color: #909399;
  text-align: center;
  border: 1px dashed #dcdfe6;
  border-radius: 4px;
}
.attachment-link {
  color: #409EFF;
  text-decoration: none;
}
.attachment-link:hover {
  text-decoration: underline;
}
</style>
