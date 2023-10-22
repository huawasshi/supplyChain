<!--
 * @Author: yanchenxi
 * @Date: 2022-06-13 20:32:22
 * @LastEditTime: 2022-06-20 14:10:46
 * @LastEditors: yanchenxi
 * @Description: 全局面包屑组件
 * @FilePath: \frontend\src\components\TopBreadcrumb.vue
-->
<template>
  <div>
    <bfe-breadcrumb separator="/">
      <bfe-breadcrumb-item
        v-for="item in levelList"
        :key="item.path"
        :to="{path:item.path}"
      >{{item.meta.title}}</bfe-breadcrumb-item>
    </bfe-breadcrumb>
  </div>
</template>

<script>
export default {
  name: 'Breadcrumb',
  data() {
    return {
      levelList: []
    };
  },
  watch: {
    $route() {
      this.getBreadcrumb();
    }
  },
  methods: {
    getBreadcrumb() {
      let levelList = this.$route.matched.filter(v => v.meta && v.meta.title);
      let last = levelList[levelList.length - 1];
      let pushList = [];
      if (last.meta.parentBreadcrumb && last.meta.parentBreadcrumb.length > 0) {
        pushList = last.meta.parentBreadcrumb.map(v => ({
          meta: v,
          path: v.path
        }));
      }
      this.levelList = levelList;
      this.levelList.splice(this.levelList.length - 1, 0, ...pushList);
      console.log(this.levelList);
    }
  },
  created() {
    this.getBreadcrumb();
  }
};
</script>

<style>
</style>