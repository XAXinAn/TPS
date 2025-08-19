import request from '@/utils/request'

// 查询任务反馈列表
export function listFeedback(query) {
  return request({
    url: '/tps/feedback/list',
    method: 'get',
    params: query
  })
}

// 查询任务反馈详细
export function getFeedback(feedbackId) {
  return request({
    url: '/tps/feedback/' + feedbackId,
    method: 'get'
  })
}

// 新增任务反馈
export function addFeedback(data) {
  return request({
    url: '/tps/feedback',
    method: 'post',
    data: data
  })
}

// 修改任务反馈
export function updateFeedback(data) {
  return request({
    url: '/tps/feedback',
    method: 'put',
    data: data
  })
}

// 删除任务反馈
export function delFeedback(feedbackId) {
  return request({
    url: '/tps/feedback/' + feedbackId,
    method: 'delete'
  })
}
