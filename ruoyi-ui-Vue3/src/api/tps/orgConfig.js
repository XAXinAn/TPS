import request from '@/utils/request'

// 查询支行管理员配置列表
export function listOrgConfig(query) {
  return request({
    url: '/tps/orgConfig/list',
    method: 'get',
    params: query
  })
}

// 查询支行管理员配置详细
export function getOrgConfig(orgUserAdminId) {
  return request({
    url: '/tps/orgConfig/' + orgUserAdminId,
    method: 'get'
  })
}

// 新增支行管理员配置
export function addOrgConfig(data) {
  return request({
    url: '/tps/orgConfig',
    method: 'post',
    data: data
  })
}

// 修改支行管理员配置
export function updateOrgConfig(data) {
  return request({
    url: '/tps/orgConfig',
    method: 'put',
    data: data
  })
}

// 删除支行管理员配置
export function delOrgConfig(orgUserAdminId) {
  return request({
    url: '/tps/orgConfig/' + orgUserAdminId,
    method: 'delete'
  })
}
