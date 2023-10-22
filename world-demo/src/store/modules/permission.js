/*
 * @Author: yanchenxi
 * @Date: 2022-05-31 16:23:11
 * @LastEditTime: 2022-06-21 11:13:11
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\store\modules\permission.js
 */
import {
    getMenu
} from '@apis/permission'
import {
    asyncRoutes,
    wildcardRoutes
} from '@router'
/**
 * @description    递归遍历返回符合的菜单
 * @param routes    后端返回的异步路由
 * @param asyncRoutes  前端定义的异步路由总集合
 */
const filterAsyncRoutes = (routes = [], asyncRoutes = []) => {
    const res = []
    console.log("routes")
    console.log(routes)
    console.log("asyncRoutes")
    console.log(asyncRoutes)
    asyncRoutes.forEach(item => {
        const rte = routes.find(route => route.path === item.path)
        if (rte) {
            if (item.children) {
                item.children = filterAsyncRoutes(rte.children, item.children)
            }
            res.push(item)
        }
    })
    return res
}

const state = {
    asyncMenu: []
}
const mutations = {
    SET_ASYNC_MENU(state, menus) {
        state.asyncMenu = menus
    }
}
const actions = {
    getAsyncMenu({
        commit
    }, data) {
        return new Promise((resolve, reject) => {
            getMenu({
                ...data
            }).then(res => {
                const routes = filterAsyncRoutes(res, asyncRoutes)
                const menus = [...routes, ...wildcardRoutes] // 加入404页面
                commit('SET_ASYNC_MENU', menus)
                resolve(menus)
            }).catch(err => {
                reject(err)
            })
        })
    }
}
export default {
    state,
    mutations,
    actions
}