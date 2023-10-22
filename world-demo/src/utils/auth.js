/*
 * @Author: yanchenxi
 * @Date: 2022-06-01 14:27:37
 * @LastEditTime: 2022-06-09 21:29:06
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\utils\auth.js
 */
const authToken = 'token'
const authUserInfo = 'userInfo'
export const setToken = (token) => {
    localStorage.setItem(authToken, token)
}
export const getToken = () => {
    return localStorage.getItem(authToken)
}
export const removeToken = () => {
    localStorage.clear()
}

export const setUserInfo = (userInfo) => {
    localStorage.setItem(authUserInfo, JSON.stringify(userInfo))
}
export const getUserInfo = () => {
    return JSON.parse(localStorage.getItem(authUserInfo))
}