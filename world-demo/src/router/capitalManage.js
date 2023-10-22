// /*
//  * @Author: yanchenxi
//  * @Date: 2022-06-10 09:43:29
//  * @LastEditTime: 2022-08-18 09:55:45
//  * @LastEditors: yanchenxi
//  * @Description: 
//  * @FilePath: \yanshi\src\router\capitalManage.js
//  */
// import Layout from '@views/layout/Layout.vue'
// const capitalManage = {
//     path: '/capitalManage',
//     redirect: '/capitalManage/capitalApply',
//     name: 'CapitalManage',
//     component: Layout,
//     meta: {
//         title: '资金管理',
//         icon: 'bfe-icon-social-yen'
//     },
//     children: [{
//             path: '/capitalManage/capitalApply',
//             name: 'CapitalApply',
//             component: () => import('@views/capitalManage/capitalApply/Index.vue'),
//             meta: {
//                 title: '资金申请',
//                 icon: ''
//             }
//         }, {
//             path: '/capitalManage/capitalApprove',
//             name: 'CapitalApprove',
//             component: () => import('@views/capitalManage/capitalApprove/Index.vue'),
//             meta: {
//                 title: '资金审批',
//                 icon: ''
//             },
//         },
//         // {
//         //     path: '/capitalManage/capitalPay',
//         //     name: 'CapitalPay',
//         //     component: () => import('@views/capitalManage/capitalPay/Index.vue'),
//         //     meta: {
//         //         title: '资金拨付',
//         //         icon: ''
//         //     },
//         // }, 
//         {
//             path: '/capitalManage/capitalSure',
//             name: 'CapitalSure',
//             component: () => import('@views/capitalManage/capitalSure/Index.vue'),
//             meta: {
//                 title: '资金确认',
//                 icon: ''
//             },
//         }
//     ]
// }
// export default capitalManage