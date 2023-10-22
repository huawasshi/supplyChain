/*
 * @Author: yanchenxi
 * @Date: 2022-06-10 09:36:12
 * @LastEditTime: 2022-08-17 20:56:10
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\router\dataTransfer.js
 */
const dataTransfer = {
    path: '/dataTransfer',
    name: 'DataTransfer',
    // redirect: '/map',
    meta: {
        title: '监管管理',
        icon: 'bfe-icon-map'
    },
    children: [{
        path: '/dataTransfer/index',
        name: 'DataTransferIndex',
        redirect: '/map',
        meta: {
            title: '疫苗监控大屏',
            icon: 'bfe-icon-clock',
        },
    }, {
        path: '/dataTransfer/addEdit',
        name: 'AddEdit',
        component: () => import('@views/dataTransfer/components/AddEdit.vue'),
        hidden: true,
        meta: {
            title: '数据迁移修改详情',
            icon: 'bfe-icon-clock',
            activeMenu: '/dataTransfer/index',
            parentBreadcrumb: [{
                path: '/dataTransfer/index',
                title: '数据迁移'
            }],
        },
    }]
}

export default dataTransfer