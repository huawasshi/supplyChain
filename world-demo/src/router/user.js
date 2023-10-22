/*
 * @Author: yanchenxi
 * @Date: 2022-06-10 09:36:12
 * @LastEditTime: 2023-10-21 15:47:50
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\src\router\user.js
 */
import Layout from '@views/layout/Layout.vue'
const dashbard = {
    path: '/dashbard',
    name: 'User',
    component: Layout,
    redirect: '/dashbard/index',
    children: [{
            path: '/dashbard/index',
            name: 'UserIndex',
            component: () => import('@views/dashbard/Index.vue'),
            meta: {
                title: '首页',
                icon: ''
            },
        },
        {
            path: '/info/index',
            name: 'InfoIndex',
            component: () => import('@views/info/Index.vue'),
            meta: {
                title: '信息录入',
                icon: ''
            },
        }

    ]
}

export default dashbard