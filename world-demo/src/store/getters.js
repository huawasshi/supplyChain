/*
 * @Author: yanchenxi
 * @Date: 2022-06-01 19:58:51
 * @LastEditTime: 2022-06-21 10:11:17
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\store\getters.js
 */
const getters = {
    asyncMenu: state => state.permission.asyncMenu, // 左侧菜单
    token: state => state.user.token, // token 
    userInfo: state => state.user.userInfo // 用户信息
}

export default getters