<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="96px">
      <el-form-item label="关联任务ID" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入关联任务ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通知接收人ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入通知接收人ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="阅读时间" prop="readTime">
        <el-date-picker clearable
          v-model="queryParams.readTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择阅读时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['tps:taskNotice:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['tps:taskNotice:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['tps:taskNotice:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['tps:taskNotice:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskNoticeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="通知唯一标识" align="center" prop="noticeId" />
      <el-table-column label="关联任务ID" align="center" prop="taskId" />
      <el-table-column label="通知接收人ID" align="center" prop="userId" />
      <el-table-column label="通知内容" align="center" prop="content" />
      <el-table-column label="通知类型" align="center" prop="noticeType" />
      <el-table-column label="阅读时间" align="center" prop="readTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.readTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['tps:taskNotice:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['tps:taskNotice:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改任务通知对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="taskNoticeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关联任务ID" prop="taskId">
          <el-input v-model="form.taskId" placeholder="请输入关联任务ID" />
        </el-form-item>
        <el-form-item label="通知接收人ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入通知接收人ID" />
        </el-form-item>
        <el-form-item label="通知内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="阅读时间" prop="readTime">
          <el-date-picker clearable
            v-model="form.readTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择阅读时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="TaskNotice">
import { listTaskNotice, getTaskNotice, delTaskNotice, addTaskNotice, updateTaskNotice } from "@/api/tps/taskNotice"

const { proxy } = getCurrentInstance()

const taskNoticeList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    taskId: null,
    userId: null,
    content: null,
    noticeType: null,
    readTime: null
  },
  rules: {
    taskId: [
      { required: true, message: "关联任务ID不能为空", trigger: "blur" }
    ],
    userId: [
      { required: true, message: "通知接收人ID不能为空", trigger: "blur" }
    ],
    content: [
      { required: true, message: "通知内容不能为空", trigger: "blur" }
    ],
    noticeType: [
      { required: true, message: "通知类型不能为空", trigger: "change" }
    ],
    createTime: [
      { required: true, message: "通知创建时间不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询任务通知列表 */
function getList() {
  loading.value = true
  listTaskNotice(queryParams.value).then(response => {
    taskNoticeList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    noticeId: null,
    taskId: null,
    userId: null,
    content: null,
    noticeType: null,
    createTime: null,
    readTime: null
  }
  proxy.resetForm("taskNoticeRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.noticeId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加任务通知"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _noticeId = row.noticeId || ids.value
  getTaskNotice(_noticeId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改任务通知"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["taskNoticeRef"].validate(valid => {
    if (valid) {
      if (form.value.noticeId != null) {
        updateTaskNotice(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addTaskNotice(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _noticeIds = row.noticeId || ids.value
  proxy.$modal.confirm('是否确认删除任务通知编号为"' + _noticeIds + '"的数据项？').then(function() {
    return delTaskNotice(_noticeIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('tps/taskNotice/export', {
    ...queryParams.value
  }, `taskNotice_${new Date().getTime()}.xlsx`)
}

getList()
</script>
