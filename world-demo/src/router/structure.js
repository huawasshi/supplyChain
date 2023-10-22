/*
 * @Author: yanchenxi
 * @Date: 2022-06-10 09:36:12
 * @LastEditTime: 2022-08-04 14:52:37
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\router\structure.js
 */
import Layout from '@views/layout/Layout.vue'
const structure = {
    path: '/structure',
    name: 'Structure',
    component: Layout,
    children: [{
            path: '/structure/index',
            name: 'StructureIndex',
            component: () => import('@views/structure/Index.vue'),
            meta: {
                title: '结构迁移',
                icon: 'bfe-icon-clock'
            },
        }, {
            path: '/structure/detail',
            name: 'StructureDetail',
            component: () => import('@views/structure/Detail.vue'),
            hidden: true,
            meta: {
                title: '结构迁移详情',
                icon: 'bfe-icon-clock',
                activeMenu: '/structure/index',
                parentBreadcrumb: [{
                    path: '/structure/index',
                    title: '结构迁移'
                }],
            },
        }, {
            path: '/structure/addDetail',
            name: 'AddDataStructureDetail',
            component: () => import('@views/structure/AddDetail.vue'),
            hidden: true,
            meta: {
                title: '结构迁移新增详情',
                icon: 'bfe-icon-clock',
                activeMenu: '/structure/index',
                parentBreadcrumb: [{
                    path: '/structure/index',
                    title: '结构迁移'
                }],
            },
        },
        //  {
        //     path: '/structure/addEdit',
        //     name: 'AddEdit',
        //     component: () => import('@views/structure/components/AddEdit1.vue'),
        //     hidden: true,
        //     meta: {
        //         title: '结构迁移修改详情',
        //         icon: 'bfe-icon-clock',
        //         activeMenu: '/structure/index',
        //         parentBreadcrumb: [{
        //             path: '/structure/index',
        //             title: '结构迁移'
        //         }],
        //     },
        // }
    ]
}

export default structure