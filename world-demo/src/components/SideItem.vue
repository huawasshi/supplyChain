<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 20:05:37
 * @LastEditTime: 2022-05-13 15:48:30
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\components\SideItem.vue
-->
<template>
  <div v-if="!item.hidden">
    <template v-if="hasOneShowingChild(item.children,item)||onlyOneChild.noShowingChildren">
      <bfe-menu-item :index="onlyOneChild.path">
        <i
          :class="onlyOneChild.meta.icon?onlyOneChild.meta.icon:'bfe-icon-menu'"
          v-if="!onlyOneChild.noShowingChildren"
        ></i>
        {{onlyOneChild.meta.title}}
      </bfe-menu-item>
    </template>
    <bfe-submenu v-else :index="item.path">
      <template slot="title">
        <i :class="item.meta.icon?item.meta.icon:'bfe-icon-menu'" v-if="item.meta"></i>
        {{item.meta.title}}
      </template>
      <side-item v-for="child in item.children" :key="child.path" :item="child"></side-item>
    </bfe-submenu>
  </div>
</template>

<script>
export default {
  name: 'SideItem', // 这个name必须赋值，不然递归组件会报错
  props: {
    item: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      onlyOneChild: null
    };
  },
  methods: {
    hasOneShowingChild(children = [], parent) {
      const showChildren = children.filter(item => {
        if (item.hidden) {
          return false;
        } else {
          this.onlyOneChild = item;
          return true;
        }
      });
      if (showChildren.length === 1) {
        return true;
      }
      if (showChildren.length === 0) {
        this.onlyOneChild = { ...parent, noShowingChildren: true };
      }
      return false;
    }
  }
};
</script>

<style>
</style>