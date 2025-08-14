import request from '@/utils/request'

// 查询任务通知列表
export function listTaskNotice(query) {
  return request({
    url: '/tps/taskNotice/list',
    method: 'get',
    params: query
  })
}

// 查询任务通知详细
export function getTaskNotice(noticeId) {
  return request({
    url: '/tps/taskNotice/' + noticeId,
    method: 'get'
  })
}

// 新增任务通知
export function addTaskNotice(data) {
  return request({
    url: '/tps/taskNotice',
    method: 'post',
    data: data
  })
}

// 修改任务通知
export function updateTaskNotice(data) {
  return request({
    url: '/tps/taskNotice',
    method: 'put',
    data: data
  })
}

// 删除任务通知
export function delTaskNotice(noticeId) {
  return request({
    url: '/tps/taskNotice/' + noticeId,
    method: 'delete'
  })
}
