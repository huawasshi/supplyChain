/*
 * @Author: yanchenxi
 * @Date: 2022-05-06 19:20:43
 * @LastEditTime: 2023-10-21 09:53:23
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\src\router\index.js
 */
import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@views/layout/Layout.vue'

import dashbard from './user'
/*
  hidden :tree      表示不在左侧菜单显示，默认为false
  name              路由名称
  meta:{
      title:'title' 左侧菜单显示的名称，顶部面包屑导航也是使用这个title
      icon:"icon"   左侧菜单使用的图标
      activeMenu：""/xx/xx" 如果设置这个路径，左侧菜单将在该路由下保持高亮，用于跳转详情时，左侧列表菜单还是高亮选中的
      parentBreadcrumb：[{{     顶部面包屑，（主要适用于类表的详情等）
                path: '/test/test2',   父级的路由
                title: '测试二级菜单test2'  父级的名称
            }}] 
  }


*/

Vue.use(Router)
// 解决左侧菜单重复点击相同路由，路由重复添加报错
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
// 无需权限就能访问的路由
export const constantRoutes = [{
        path: '/',
        component: Layout,
        redirect: '/dashbard/index'
    },
    {
        path: '/login',
        component: () => import('@views/login/Login.vue'),
        hidden: true
    },
    {
        path: '/map',
        component: () => import('@views/map/Index.vue'),
        hidden: true
    },
    {
        path: '/404',
        component: () => import('@/views/404/Index.vue'),
        hidden: true
    },
]
// 通用404 page 必须挂载在最末尾
export const wildcardRoutes = [{
    path: '/*',
    redirect: '/404',
    hidden: true
}]
// 异步路由
export const asyncRoutes = [dashbard]
export const allRouter = [...constantRoutes, ...asyncRoutes, ...wildcardRoutes]

const createRouter = () => new Router({
    scrollBehavior: () => ({
        y: 0
    }),
    routes: allRouter
})

const router = createRouter()
export default router