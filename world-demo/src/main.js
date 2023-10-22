/*
 * @Author: yanchenxi
 * @Date: 2022-05-06 19:20:43
 * @LastEditTime: 2023-10-21 10:40:36
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\src\main.js
 */
import Vue from 'vue'
import App from './App.vue'


import ElementUI from 'element-ui'
import store from '@/store'
import 'element-ui/lib/theme-chalk/index.css'

import '@style/index.less'
import router from '@router'
Vue.config.productionTip = false

Vue.use(ElementUI)
new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')