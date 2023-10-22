/*
 * @Author: yanchenxi
 * @Date: 2022-06-10 09:36:12
 * @LastEditTime: 2022-06-20 13:34:20
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\router\timeing.js
 */
import Layout from '@views/layout/Layout.vue'
const timeing = {
    path: '/timeing',
    name: 'Timeing',
    component: Layout,
    redirect: '/timeing/index',
    children: [{
        path: '/timeing/index',
        name: 'TimeingIndex',
        component: () => import('@views/timeing/Index.vue'),
        meta: {
            title: '定时调度',
            icon: 'bfe-icon-clock'
        },
    }, {
        path: '/timeing/detail',
        name: 'TimeingDetail',
        component: () => import('@views/timeing/Detail.vue'),
        hidden: true,
        meta: {
            title: '定时调度详情',
            icon: 'bfe-icon-clock',
            activeMenu: '/timeing/index',
            parentBreadcrumb: [{
                path: '/timeing/index',
                title: '定时调度'
            }],
        },
    }]
}
export default timeing