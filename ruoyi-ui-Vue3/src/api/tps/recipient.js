import request from '@/utils/request'

// 查询下发对象列表
export function listRecipient(query) {
  return request({
    url: '/tps/recipient/list',
    method: 'get',
    params: query
  })
}

// 查询下发对象详细
export function getRecipient(recipientId) {
  return request({
    url: '/tps/recipient/' + recipientId,
    method: 'get'
  })
}

// 新增下发对象
export function addRecipient(data) {
  return request({
    url: '/tps/recipient',
    method: 'post',
    data: data
  })
}

// 修改下发对象
export function updateRecipient(data) {
  return request({
    url: '/tps/recipient',
    method: 'put',
    data: data
  })
}

// 删除下发对象
export function delRecipient(recipientId) {
  return request({
    url: '/tps/recipient/' + recipientId,
    method: 'delete'
  })
}
