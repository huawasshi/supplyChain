<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 10:18:53
 * @LastEditTime: 2023-10-21 10:41:27
 * @LastEditors: yanchenxi
 * @Description: 项目页面容器
 * @FilePath: \world-demo\src\views\layout\Layout.vue
-->
<template>
  <el-container class="contain">
    <el-container>
      <el-header>
        <layout-header />
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import LayoutHeader from './LayoutHeader';
export default {
  name: 'Layout',
  components: { LayoutHeader }
};
</script>

<style lang="less" scoped>
.el-header {
  border-bottom: 2px solid #4672cf;
  border-image: url('../../assets/dashborad/line.png') 100 30 round;
  border-image-repeat: repeated;

  color: #333;
  text-align: center;
  line-height: 60px;
  margin: 0 50px;
}

.contain {
  background-image: url('../../assets/dashborad/bg.png');
  background-size: 100% 100%;
  background-repeat: no-repeat;
  height: 100vh;
}
</style>