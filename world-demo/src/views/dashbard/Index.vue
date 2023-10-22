<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 20:57:51
 * @LastEditTime: 2023-10-18 10:36:41
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\src\views\dashbard\Index.vue
-->
<template>
  <div class="content">
    <div class="content-top"></div>
    <div class="content-main">
      <div class="content-main-left" @click="goInfo" v-if="userInfo.registration_type!=='消费者'"></div>
      <div class="content-main-right" @click="goMap"></div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  data() {
    return {
      role: 'admin'
    };
  },
  computed: {
    ...mapGetters(['userInfo'])
  },

  methods: {
    goInfo() {
      this.$router.push({ path: '/info/index' });
    },
    goMap() {
      this.$router.push({ path: '/map' });
    }
  }
};
</script>

<style lang="less" scoped>
.content {
  margin: 0 50px;
  // background: red;
  height: calc(100vh - 60px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  &-top {
    background-image: url('../../assets/dashborad/title1.png');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    width: 80%;
    height: 35px;
    margin-bottom: 100px;
  }
  &-main {
    display: flex;
    width: 70%;
    justify-content: space-around;
    &-left {
      background-image: url('../../assets/dashborad/xx.png');
      background-size: 100% 100%;
      background-repeat: no-repeat;
      width: 450px;
      height: 400px;
      cursor: pointer;
    }
    &-right {
      background-image: url('../../assets/dashborad/sy.png');
      background-size: 100% 100%;
      background-repeat: no-repeat;
      width: 450px;
      height: 400px;
      cursor: pointer;
    }
  }
}
</style>
----