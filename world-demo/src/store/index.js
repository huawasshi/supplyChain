/*
 * @Author: yanchenxi
 * @Date: 2022-05-13 16:24:54
 * @LastEditTime: 2022-06-21 10:11:37
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\store\index.js
 */
import Vue from 'vue'
import Vuex from 'vuex'
import path from 'path'
import getters from './getters'

const fileModules = require.context('./modules', false, /\.js$/)
// 模块导入
const modules = fileModules.keys().reduce((fileModule, key) => {
    const moduleName = path.basename(key, '.js')
    fileModule[moduleName] = fileModules(key).default || fileModules(key)
    return fileModule
}, {})

Vue.use(Vuex)

const store = new Vuex.Store({
    modules,
    getters
})
export default store