import request from '@/utils/request'

export function listAllOrgs() {
    return request({
        url: '/task/org/listAllSimple',
        method: 'get'
    })
}
