/*
 * @Author: yanchenxi
 * @Date: 2022-05-31 16:22:43
 * @LastEditTime: 2023-10-18 09:53:51
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\src\store\modules\user.js
 */
import {
    loginOn,
    loginOut
} from '@apis/user'
import {
    setToken,
    getToken,
    setUserInfo,
    getUserInfo
} from '@utils/auth'
const state = {
    token: getToken(),
    userInfo: getUserInfo()
}
const mutations = {
    SET_TOKEN(state, token) {
        state.token = token
        setToken(token)
    },
    SET_USERINFO(state, userInfo) {
        state.userInfo = userInfo
        setUserInfo(userInfo)
    }
}
const actions = {
    // user   login
    // 登陆
    login({
        commit
    }, data) {
        return new Promise((resolve, reject) => {
            loginOn({
                ...data
            }).then(res => {
                const token = '123123'
                commit('SET_TOKEN', token)
                const userInfo = res
                commit('SET_USERINFO', userInfo)
                resolve(res)
            }).catch(err => {
                reject(err)
            })
        })
    },
    // 登出
    loginOut({
        commit
    }, data) {
        return new Promise((resolve, reject) => {
            loginOut({
                ...data
            }).then(res => {
                commit('SET_TOKEN', '')
                commit('SET_USERINFO', null)
                resolve(res)
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