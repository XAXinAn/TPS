<template>
  <div class="app-container">
    <!-- Task Table -->
    <el-table :data="tasks" v-loading="loading" style="width: 100%">
      <el-table-column prop="title" label="任务标题" width="180"></el-table-column>
      <el-table-column prop="description" label="描述" show-overflow-tooltip></el-table-column>
      <el-table-column prop="deadline" label="截止日期" width="120"></el-table-column>
      <el-table-column prop="priority" label="优先级" width="100"></el-table-column>
      <el-table-column prop="recipientType" label="接收方类型" width="100"></el-table-column>
      <el-table-column prop="needConfirm" label="是否需确认" width="120">
        <template #default="scope">
          <span>{{ scope.row.needConfirm ? '是' : '否' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="任务状态" width="120"></el-table-column>
      <el-table-column label="操作" width="220">
        <template #default="scope">
          <el-button
              link
              type="primary"
              @click="handleDownload(scope.row)"
              v-if="scope.row.haveAttachment"
          >附件</el-button>
          <el-button
              link
              type="danger"
              @click="handleFeedback(scope.row)"
          >反馈</el-button>
          <el-button
              link
              type="warning"
              @click="handleSplit(scope.row)"
              v-if="scope.row.recipientType === '支行管理层'"
          >拆分</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- Feedback Dialog -->
    <el-dialog title="任务反馈" v-model="feedbackFormVisible" width="800px" append-to-body>
      <el-form ref="feedbackFormRef" :model="feedbackForm" :rules="feedbackRules" label-width="80px">
        <el-form-item label="反馈状态" prop="status">
          <el-select v-model="feedbackForm.status" placeholder="请选择状态">
            <el-option label="待确认" value="待确认"></el-option>
            <el-option label="已完成" value="已完成"></el-option>
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

const tasks = ref([]);
const loading = ref(true);
const feedbackFormVisible = ref(false);
const feedbackFormRef = ref(null);

const feedbackForm = reactive({
  taskId: null,
  status: '',
  comment: '',
  createBy: null,
  attachments: ''
});

const feedbackRules = reactive({
  status: [{ required: true, message: "反馈状态不能为空", trigger: "change" }],
  comment: [{ required: true, message: "评论或原因不能为空", trigger: "blur" }]
});

/**
 * 获取任务列表
 */
function getTaskList() {
  loading.value = true;
  listEmployeeTasks().then(response => {
    tasks.value = response.data;
    loading.value = false;
  });
}

/** 提交反馈 */
function handleFeedback(row) {
  feedbackForm.taskId = row.taskId;
  if (feedbackFormRef.value) {
    feedbackFormRef.value.resetFields();
  }
  feedbackFormVisible.value = true;
}

/** 提交反馈表单 */
function submitFeedbackForm() {
  feedbackFormRef.value.validate(valid => {
    if (valid) {
      feedbackForm.createBy = userStore.id; // 添加创建人ID
      submitFeedback(feedbackForm).then(response => {
        proxy.$modal.msgSuccess("反馈提交成功");
        feedbackFormVisible.value = false;
        getTaskList(); // 刷新列表
      });
    }
  });
}

/** 取消反馈 */
function cancelFeedback() {
  feedbackFormVisible.value = false;
}

/** 任务拆分 */
function handleSplit(row) {
  console.log("拆分任务:", row);
  proxy.$modal.msgSuccess("拆分任务信息已打印到控制台");
}

/** 下载附件 */
function handleDownload(row) {
  if (row.attachmentUrl && Array.isArray(row.attachmentUrl) && row.attachmentUrl.length > 0) {
    row.attachmentUrl.forEach(url => {
      const link = document.createElement('a');
      link.href = import.meta.env.VITE_APP_BASE_API + url;
      link.style.display = 'none';
      link.download = url.substring(url.lastIndexOf('/') + 1);
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    });
  } else {
    proxy.$modal.msgError("没有可下载的附件");
  }
}

onMounted(() => {
  getTaskList();
});

</script>

<style scoped>
.app-container {
  padding: 20px;
}
</style>

