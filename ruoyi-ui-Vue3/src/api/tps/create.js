import request from '@/utils/request'

export function listAllOrgs() {
    return request({
        url: '/tps/org/listAllOrgs',
        method: 'get'
    })
}

export function createTask(data) {
    return request({
        url: '/tps/task/createTask',
        method: 'post',
        data: data
    })
}