/*
 * @Author: yanchenxi
 * @Date: 2022-06-10 09:43:29
 * @LastEditTime: 2022-08-20 17:14:51
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\router\test.js
 */
import Layout from '@views/layout/Layout.vue'
const test = {
    path: '/test',
    redirect: '/test/test1',
    name: 'Test',
    component: Layout,
    meta: {
        title: '订单管理',
        icon: 'bfe-icon-clipboard'
    },
    children: [{
        path: '/test/test1',
        name: 'Test1',
        component: () => import('@views/test/test1/Index.vue'),
        meta: {
            title: '订单查询',
            icon: ''
        }
    }, {
        path: '/test/test2',
        name: 'Test2',
        component: () => import('@views/test/test2/Index.vue'),
        meta: {
            title: '订单新建',
            icon: ''
        },
    }, {
        path: '/test/test4/detail',
        name: 'Test4Detail',
        component: () => import('@views/test/test4/Detail.vue'),
        hidden: true,
        parentBreadcrumb: '/test/test4',
        meta: {
            title: '订单跟踪详情',
            activeMenu: '/test/test4',
            icon: '',
            parentBreadcrumb: [{
                path: '/test/test4',
                title: '订单跟踪'
            }],
        },
    }, {
        path: '/test/test3',
        // redirect: '/map',
        component: () => import('@views/test/test3/Index.vue'),
        meta: {
            title: '订单审核',
            icon: ''
        },
    }, {
        path: '/test/test4',
        component: () => import('@views/test/test4/Index.vue'),
        meta: {
            title: '订单跟踪',
            icon: ''
        },
    }]
}
export default test