import request from '@/utils/request'

// 查询员工任务列表
export function listOrgAdminTasks() {
    return request({
        url: '/tps/orgAdmin/listOrgAdminTasks',
        method: 'get'
    })
}
