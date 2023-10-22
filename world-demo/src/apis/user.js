/*
 * @Author: yanchenxi
 * @Date: 2022-05-06 19:20:43
 * @LastEditTime: 2023-10-18 16:11:18
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\src\apis\user.js
 */
import request from './request'

/**
 * @description: 登陆
 * @param {Object} 
 * @return {Promise} 
 */
export const loginOn = (data) => {
    return request({
        method: 'post',
        url: '/api/fabricUser/login',
        // url: '/datax/login',
        data
    })
}
/**
 * @description: 注册
 * @param {Object} 
 * @return {Promise} 
 */
export const registerOn = (data) => {
    return request({
        method: 'post',
        url: '/api/fabricUser/register',
        data
    })
}
/**
 * @description: 退出
 * @param {Object} 
 * @return {Promise} 
 */
export const loginOut = (data) => {
    return request({
        method: 'post',
        url: '/datax/loginOut',
        data
    })
}

export const fabricTraceUpdata = (data) => {
    return request({
        method: 'post',
        url: '/api/fabricTrace/updata',
        data
    })
}

export const findByCainId = (params) => {
    return request({
        method: 'get',
        url: '/api/fabricTrace/findByCainId',
        params
    })
}