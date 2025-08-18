<template>
  <div class="app-container">
    <el-table :data="tasks" v-loading="loading" style="width: 100%">
      <el-table-column prop="title" label="任务标题" width="180"></el-table-column>
      <el-table-column prop="description" label="描述" show-overflow-tooltip></el-table-column>
      <el-table-column prop="deadline" label="截止日期" width="120"></el-table-column>
      <el-table-column prop="priority" label="优先级" width="100"></el-table-column>
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
          >下载附件</el-button>
          <!-- 其他操作按钮可以放在这里 -->
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted, getCurrentInstance } from 'vue';

const { proxy } = getCurrentInstance();

const tasks = ref([]);
const loading = ref(true);

/**
 * 获取任务列表
 * TODO: 后端接口准备好后，请替换这里的模拟数据为真实的API调用
 */
function getTaskList() {
  loading.value = true;
  // 模拟API调用
  setTimeout(() => {
    tasks.value = [
      {
        taskId: 1,
        title: '员工任务一',
        description: '这是分配给普通员工的第一个任务的详细描述。',
        deadline: '2025-09-01',
        priority: 1,
        needConfirm: true,
        status: '进行中',
        haveAttachment: true,
        attachmentUrl: ['/profile/upload/2025/08/18/mock_attachment.docx']
      },
      {
        taskId: 2,
        title: '员工任务二',
        description: '这是第二个任务，没有附件，也不需要确认。',
        deadline: '2025-09-05',
        priority: 2,
        needConfirm: false,
        status: '待处理',
        haveAttachment: false,
        attachmentUrl: []
      }
    ];
    loading.value = false;
  }, 500);
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
