/*
 * @Author: yanchenxi
 * @Date: 2022-06-10 09:36:12
 * @LastEditTime: 2022-08-15 20:10:45
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\router\dataManage.js
 */
import Layout from '@views/layout/Layout.vue'
const dataManage = {
    path: '/dataManage',
    name: 'DataManage',
    redirect: "/dataManage/index",
    component: Layout,
    children: [{
        path: '/dataManage/index',
        name: 'Test2Detail',
        component: () => import('@views/dataManage/Index.vue'),
        meta: {
            title: '资金管理',
            icon: ''
        },
    }]
}

export default dataManage