import request from '@/utils/request'

// 查询创建任务列表
export function listTaskMain(query) {
  return request({
    url: '/tps/taskMain/list',
    method: 'get',
    params: query
  })
}

// 查询创建任务详细
export function getTaskMain(taskId) {
  return request({
    url: '/tps/taskMain/' + taskId,
    method: 'get'
  })
}

// 新增创建任务
export function addTaskMain(data) {
  return request({
    url: '/tps/taskMain',
    method: 'post',
    data: data
  })
}

// 修改创建任务
export function updateTaskMain(data) {
  return request({
    url: '/tps/taskMain',
    method: 'put',
    data: data
  })
}

// 删除创建任务
export function delTaskMain(taskId) {
  return request({
    url: '/tps/taskMain/' + taskId,
    method: 'delete'
  })
}
