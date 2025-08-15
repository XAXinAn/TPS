<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="124px">
      <el-form-item label="任务标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入任务标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="截止日期" prop="deadline">
        <el-date-picker clearable
          v-model="queryParams.deadline"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择截止日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="优先级 " prop="priority">
        <el-input
          v-model="queryParams.priority"
          placeholder="请输入优先级 "
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否需发起人确认" prop="needConfirm">
        <el-input
          v-model="queryParams.needConfirm"
          placeholder="请输入是否需发起人确认"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人ID" prop="creatorId">
        <el-input
          v-model="queryParams.creatorId"
          placeholder="请输入创建人ID"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['tps:task:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['tps:task:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['tps:task:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['tps:task:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务id" align="center" prop="taskId" />
      <el-table-column label="任务标题" align="center" prop="title" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="截止日期" align="center" prop="deadline" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.deadline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="优先级 " align="center" prop="priority" />
      <el-table-column label="是否需发起人确认" align="center" prop="needConfirm" />
      <el-table-column label="任务状态" align="center" prop="status" />
      <el-table-column label="创建人ID" align="center" prop="creatorId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['tps:task:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['tps:task:remove']">删除</el-button>
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

    <!-- 添加或修改任务对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="taskRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入任务标题" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="截止日期" prop="deadline">
          <el-date-picker clearable
            v-model="form.deadline"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择截止日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="优先级 " prop="priority">
          <el-input v-model="form.priority" placeholder="请输入优先级 " />
        </el-form-item>
        <el-form-item label="是否需发起人确认" prop="needConfirm">
          <el-input v-model="form.needConfirm" placeholder="请输入是否需发起人确认" />
        </el-form-item>
        <el-form-item label="创建人ID" prop="creatorId">
          <el-input v-model="form.creatorId" placeholder="请输入创建人ID" />
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

<script setup name="Task">
import { listTask, getTask, delTask, addTask, updateTask } from "@/api/tps/task"

const { proxy } = getCurrentInstance()

const taskList = ref([])
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
    title: null,
    description: null,
    deadline: null,
    priority: null,
    needConfirm: null,
    status: null,
    creatorId: null,
  },
  rules: {
    title: [
      { required: true, message: "任务标题不能为空", trigger: "blur" }
    ],
    description: [
      { required: true, message: "描述不能为空", trigger: "blur" }
    ],
    deadline: [
      { required: true, message: "截止日期不能为空", trigger: "blur" }
    ],
    priority: [
      { required: true, message: "优先级 不能为空", trigger: "blur" }
    ],
    needConfirm: [
      { required: true, message: "是否需发起人确认不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "任务状态不能为空", trigger: "change" }
    ],
    creatorId: [
      { required: true, message: "创建人ID不能为空", trigger: "blur" }
    ],
    createTime: [
      { required: true, message: "创建时间不能为空", trigger: "blur" }
    ],
    updateTime: [
      { required: true, message: "更新时间不能为空", trigger: "blur" }
    ]
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询任务列表 */
function getList() {
  loading.value = true
  listTask(queryParams.value).then(response => {
    taskList.value = response.rows
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
    taskId: null,
    title: null,
    description: null,
    deadline: null,
    priority: null,
    needConfirm: null,
    status: null,
    creatorId: null,
    createTime: null,
    updateTime: null
  }
  proxy.resetForm("taskRef")
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
  ids.value = selection.map(item => item.taskId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加任务"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _taskId = row.taskId || ids.value
  getTask(_taskId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改任务"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["taskRef"].validate(valid => {
    if (valid) {
      if (form.value.taskId != null) {
        updateTask(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addTask(form.value).then(response => {
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
  const _taskIds = row.taskId || ids.value
  proxy.$modal.confirm('是否确认删除任务编号为"' + _taskIds + '"的数据项？').then(function() {
    return delTask(_taskIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('tps/task/export', {
    ...queryParams.value
  }, `task_${new Date().getTime()}.xlsx`)
}


getList()
</script>
