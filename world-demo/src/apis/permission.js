/*
 * @Author: yanchenxi
 * @Date: 2022-05-06 19:20:43
 * @LastEditTime: 2022-06-01 19:32:12
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\apis\permission.js
 */
import request from './request'

/**
 * @description: 获取权限菜单
 * @param {Object} 
 * @return {Promise} 
 */
export const getMenu = (data) => {
    return request({
        method: 'post',
        url: '/datax/menu',
        data
    })
}