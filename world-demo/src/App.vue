<!--
 * @Author: yanchenxi
 * @Date: 2022-05-06 14:21:53
 * @LastEditTime: 2022-05-09 15:50:16
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\App.vue
-->
<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {}
};
</script>

<style lang="less" scoped>
#app {
  /* color: red; */
}
.test {
  color: @color-red;
}
</style>
