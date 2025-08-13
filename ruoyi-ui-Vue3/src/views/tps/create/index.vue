<template>
  <div class="app-container">
    <el-form ref="创建任务" :model="formData" :rules="rules" size="default" label-width="100px">
      <el-form-item label="任务标题" prop="title">
        <el-input v-model="formData.title" type="text" placeholder="请输入任务标题" clearable
                  :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input v-model="formData.description" type="textarea" placeholder="请输入描述"
                  :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="截止日期" prop="deadline">
        <el-date-picker v-model="formData.deadline" format="YYYY-MM-DD" value-format="YYYY-MM-DD"
                        :style="{width: '100%'}" placeholder="请选择截止日期" clearable></el-date-picker>
      </el-form-item>
      <el-form-item label-width="106px" label="下发对象类型" prop="recipientType">
        <el-radio-group v-model="formData.recipientType" size="default">
          <el-radio v-for="(item, index) in recipientTypeOptions" :key="index" :value="item.value"
                    :disabled="item.disabled">{{item.label}}</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="下发对象" prop="orgId" v-if="formData.recipientType === 1">
        <el-select
            v-model="formData.orgId"
            placeholder="请选择下发对象"
            multiple
            filterable
            clearable
            :style="{width: '100%'}"
            :loading="orgLoading">
          <el-option
              v-for="item in orgOptions"
              :key="item.orgId"
              :label="item.org1Id + ' - ' + item.orgName"
              :value="item.orgId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="下发对象" prop="userId" v-if="formData.recipientType === 2">
        <el-select
            v-model="formData.userId"
            placeholder="请选择下发对象"
            multiple
            filterable
            clearable
            :style="{width: '100%'}"
            :loading="userLoading">
          <el-option
              v-for="item in userOptions"
              :key="item.userId"
              :label="item.userName + ' - ' + item.nickName"
              :value="item.userId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="优先级" prop="priority">
        <el-input-number v-model="formData.priority" placeholder="优先级"
                         controls-position=right></el-input-number>
      </el-form-item>
      <el-form-item label-width="106px" label="需发起人确认" prop="confirm" required>
        <el-switch v-model="formData.confirm"></el-switch>
      </el-form-item>
      <el-form-item label="上传" prop="attachment">
<!--        <el-upload ref="attachment" :file-list="attachmentfileList" :action="attachmentAction" multiple-->
<!--                   :before-upload="attachmentBeforeUpload">-->
<!--          <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>-->
<!--        </el-upload>-->
        <FileUpload v-model="formData.attachment"
          :file-size="50"
          :file-type="['doc', 'docx', 'pdf', 'png', 'jpg']"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script setup>

import { ref, onMounted } from 'vue'
import { listAllUsers } from '@/api/system/user'
import { listAllOrgs, createTask } from '@/api/tps/create'
import FileUpload from '@/components/FileUpload/index.vue';

const userOptions = ref([])
const userLoading = ref(false)
const orgOptions = ref([])
const orgLoading = ref(false)

const {
  proxy
} = getCurrentInstance()
const 创建任务 = ref()
const data = reactive({
  formData: {
    title: '',
    description: undefined,
    deadline: null,
    recipientType: 1,
    userId: [],
    orgId: [],
    priority: undefined,
    confirm: false,
    attachment: "",
  },
  rules: {
    title: [{
      required: true,
      message: '请输入任务标题',
      trigger: 'blur'
    }],
    description: [{
      required: true,
      message: '请输入描述',
      trigger: 'blur'
    }],
    deadline: [{
      required: true,
      message: '请选择截止日期',
      trigger: 'change'
    }],
    recipientType: [{
      required: true,
      message: '下发对象类型不能为空',
      trigger: 'change'
    }],
    userId: [{
      required: true,
      type: 'array',
      message: '请至少选择一个员工',
      trigger: 'change'
    }],
    orgId: [{
      required: true,
      type: 'array',
      message: '请至少选择一个支行',
      trigger: 'change'
    }],
    priority: [{
      required: true,
      message: '优先级',
      trigger: 'blur'
    }],
  }
})
const {
  formData,
  rules
} = toRefs(data)
const recipientTypeOptions = ref([{
  "label": "支行管理层",
  "value": 1
}, {
  "label": "普通员工",
  "value": 2
}])

/**
 * @name: 表单提交
 * @description: 表单提交方法
 * @return {*}
 */
function submitForm() {
  创建任务.value.validate((valid) => {
    if (!valid) return

    // 准备要提交的数据
    const submitData = {
      title: formData.value.title,
      description: formData.value.description,
      deadline: formData.value.deadline,
      recipientType: formData.value.recipientType,
      // 根据接收者类型选择不同的ID数组
      recipients: formData.value.recipientType === 1
          ? formData.value.orgId
          : formData.value.userId,
      priority: formData.value.priority,
      confirm: formData.value.confirm,
      // 如果有附件信息也可以加上
      attachment: formData.value.attachment
    }

    // 调用API提交数据
    proxy.$modal.msgSuccess('表单数据已提交，请查看后端控制台')
    console.log('准备提交的表单数据:', submitData)

    // TODO: 这里替换为实际的API调用
    createTask(submitData).then(res => {
      proxy.$modal.msgSuccess('提交成功')
    }).catch(err => {
      proxy.$modal.msgError('提交失败')
    })
  })
}
/**
 * @name: 表单重置
 * @description: 表单重置方法
 * @return {*}
 */
function resetForm() {
  创建任务.value.resetFields()
}


// 加载用户数据
const loadUsers = async () => {
  try {
    userLoading.value = true
    const res = await listAllUsers() // 调用获取用户列表的API
    userOptions.value = res.data.map(user => ({
      userId: user.userId,
      nickName: user.nickName,
      userName: user.userName,
    }))
  } catch (error) {
    console.error('加载用户列表失败:', error)
  } finally {
    userLoading.value = false
  }
}

// 组件挂载时加载用户数据
onMounted(() => {
  loadUsers()
})

// 加载支行数据
const loadOrgs = async () => {
  try {
    orgLoading.value = true
    const res = await listAllOrgs() // 调用获取支行列表的API
    orgOptions.value = res.data.map(org => ({
      orgId: org.org_id,    // 数据库字段名
      orgName: org.org_name, // 数据库字段名
      org1Id: org.org1_id,
    }))
  } catch (error) {
    console.error('加载支行列表失败:', error)
  } finally {
    orgLoading.value = false
  }
}

// 组件挂载时加载支行数据
onMounted(() => {
  loadOrgs()
})

</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}

</style>
