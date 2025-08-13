<template>
  <div class="app-container">
    <el-form ref="formRef" :model="formData" :rules="rules" size="default" label-width="100px">
      <el-form-item label="任务标题" prop="title">
        <el-input v-model="formData.title" type="text" placeholder="请输入任务标题" clearable :style="{width: '100%'}"></el-input>
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
        <el-select v-model="formData.orgId" placeholder="请选择下发对象" multiple filterable clearable
                   :style="{width: '100%'}" :loading="orgLoading">
          <el-option v-for="item in orgOptions" :key="item.orgId" :label="item.org1Id + ' - ' + item.orgName"
                     :value="item.orgId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="下发对象" prop="userId" v-if="formData.recipientType === 2">
        <el-select v-model="formData.userId" placeholder="请选择下发对象" multiple filterable clearable
                   :style="{width: '100%'}" :loading="userLoading">
          <el-option v-for="item in userOptions" :key="item.userId" :label="item.userName + ' - ' + item.nickName"
                     :value="item.userId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="优先级" prop="priority">
        <el-input-number v-model="formData.priority" placeholder="优先级" controls-position=right></el-input-number>
      </el-form-item>
      <el-form-item label-width="106px" label="需发起人确认" prop="confirm" required>
        <el-switch v-model="formData.confirm"></el-switch>
      </el-form-item>
      <el-form-item label="上传" prop="attachment">
        <FileUpload v-model="formData.attachment"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref, reactive, toRefs, onMounted, getCurrentInstance } from 'vue';
import { listAllUsers } from '@/api/system/user';
import { listAllOrgs, createTask } from '@/api/tps/create';
import FileUpload from '@/components/FileUpload/index.vue';

// =========== 组件实例、表单引用 ===========
const { proxy } = getCurrentInstance();
const formRef = ref();

// =========== 响应式状态定义 ===========
const userOptions = ref([]);
const userLoading = ref(false);
const orgOptions = ref([]);
const orgLoading = ref(false);
const recipientTypeOptions = ref([
  { "label": "支行管理层", "value": 1 },
  { "label": "普通员工", "value": 2 }
]);

const formState = reactive({
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
    title: [{ required: true, message: '请输入任务标题', trigger: 'blur' }],
    description: [{ required: true, message: '请输入描述', trigger: 'blur' }],
    deadline: [{ required: true, message: '请选择截止日期', trigger: 'change' }],
    recipientType: [{ required: true, message: '下发对象类型不能为空', trigger: 'change' }],
    userId: [{ required: true, type: 'array', message: '请至少选择一个员工', trigger: 'change' }],
    orgId: [{ required: true, type: 'array', message: '请至少选择一个支行', trigger: 'change' }],
    priority: [{ required: true, message: '优先级', trigger: 'blur' }],
  }
});
const { formData, rules } = toRefs(formState);


// =========== 生命周期钩子 ===========
onMounted(() => {
  loadUsers();
  loadOrgs();
});


// =========== 数据加载方法 ===========
const loadUsers = async () => {
  try {
    userLoading.value = true;
    const res = await listAllUsers();
    userOptions.value = res.data.map(user => ({
      userId: user.userId,
      nickName: user.nickName,
      userName: user.userName,
    }));
  } catch (error) {
    console.error('加载用户列表失败:', error);
  } finally {
    userLoading.value = false;
  }
};

const loadOrgs = async () => {
  try {
    orgLoading.value = true;
    const res = await listAllOrgs();
    orgOptions.value = res.data.map(org => ({
      orgId: org.org_id,
      orgName: org.org_name,
      org1Id: org.org1_id,
    }));
  } catch (error) {
    console.error('加载支行列表失败:', error);
  } finally {
    orgLoading.value = false;
  }
};


// =========== 表单与事件处理方法 ===========
function submitForm() {
  formRef.value.validate((valid) => {
    if (!valid) return;

    const submitData = {
      ...formData.value,
      recipients: formData.value.recipientType === 1
          ? formData.value.orgId
          : formData.value.userId,
    };
    // 清理掉不再需要单独提交的字段
    delete submitData.orgId;
    delete submitData.userId;

    console.log('准备提交的表单数据:', submitData);
    createTask(submitData).then(res => {
      proxy.$modal.msgSuccess('提交成功');
    }).catch(err => {
      proxy.$modal.msgError('提交失败');
    });
  });
}

function resetForm() {
  formRef.value.resetFields();
}
</script>

<style>
.el-upload__tip {
  line-height: 1.2;
}
</style>