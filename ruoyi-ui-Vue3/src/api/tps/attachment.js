import request from '@/utils/request'

// 查询任务附件列表
export function listAttachment(query) {
  return request({
    url: '/tps/attachment/list',
    method: 'get',
    params: query
  })
}

// 查询任务附件详细
export function getAttachment(attachmentId) {
  return request({
    url: '/tps/attachment/' + attachmentId,
    method: 'get'
  })
}

// 新增任务附件
export function addAttachment(data) {
  return request({
    url: '/tps/attachment',
    method: 'post',
    data: data
  })
}

// 修改任务附件
export function updateAttachment(data) {
  return request({
    url: '/tps/attachment',
    method: 'put',
    data: data
  })
}

// 删除任务附件
export function delAttachment(attachmentId) {
  return request({
    url: '/tps/attachment/' + attachmentId,
    method: 'delete'
  })
}
