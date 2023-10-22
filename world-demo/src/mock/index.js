/*
 * @Author: yanchenxi
 * @Date: 2022-05-13 16:24:54
 * @LastEditTime: 2022-05-31 11:24:39
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\mock\index.js
 */
import path from 'path'
import Mock from 'mockjs'
const fileModules = require.context('.', false, /\.js$/)
const apisList = []
fileModules.keys().forEach((key) => {
    const fileModuleName = path.basename(key, '.js')
    if (fileModuleName !== 'index') {
        const list = fileModules(key).default || fileModules(key)
        apisList.push(...list)
    }
})
apisList.forEach(item => {
    Mock.mock(item.url, item.method, item.data)
})

export default Mock