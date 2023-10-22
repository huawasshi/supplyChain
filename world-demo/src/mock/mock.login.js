/*
 * @Author: yanchenxi
 * @Date: 2022-05-13 16:40:53
 * @LastEditTime: 2022-06-09 21:02:52
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\mock\mock.login.js
 */
const login = [{
    url: '/mock/login',
    method: 'post',
    data: {
        message: '登陆成功',
        code: '000000',
        data: {
            name: '艾斯',
            id: '1111',
            token: '312312eqweqwewq'
        }
    }
}, {
    url: '/mock/loginOut',
    method: 'post',
    data: {
        message: '退出成功成功',
        code: '000000',
        data: {
            test: '12321'
        }
    }
}]
export default login