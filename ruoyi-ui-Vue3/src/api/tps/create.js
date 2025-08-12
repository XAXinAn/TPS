import request from '@/utils/request'

export function listAllOrgs() {
    return request({
        url: '/tps/org/listAllOrgs',
        method: 'get'
    })
}

export function createTask(data) {
    return request({
        url: '/tps/org/createTask',
        method: 'post',
        data: data
    })
}