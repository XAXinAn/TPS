import request from '@/utils/request'

// 查询员工任务列表
export function listEmployeeTasks() {
  return request({
    url: '/tps/employee/listEmployeeTasks',
    method: 'get'
  })
}

// 提交任务反馈
export function submitFeedback(data) {
  return request({
    url: '/tps/employee/submitFeedback',
    method: 'post',
    data: data
  })
}
