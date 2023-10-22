<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 10:21:08
 * @LastEditTime: 2023-10-18 16:33:32
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\src\views\layout\LayoutHeader.vue
-->
<template>
  <div>
    <div class="warp">
      <div class="title">Food safety scenarios</div>
      <div class="out">
        <div>
          <img src="../../assets/dashborad/tb1.png" alt />
          <span>{{userInfo.name}}</span>
        </div>
        <div>
          <img src="../../assets/dashborad/tb2.png" alt />
          <span>{{userInfo.registration_type}}</span>
        </div>
        <div @click="goOut">
          <img src="../../assets/dashborad/tb3.png" alt />
          <span>log out</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  name: 'LayoutHeader',
  data() {
    return {
      isDashBoard: false
    };
  },
  computed: {
    ...mapGetters(['userInfo'])
  },

  methods: {
    goOut() {
      this.$router.push({ path: '/login' });
    }
  },

  mounted() {}
};
</script>

<style lang="less" scoped>
.warp {
  color: #cae7ff;
  display: flex;
  justify-content: space-between;
  .title {
    font-size: 24px;
  }
  .out {
    display: flex;
    img {
      margin-left: 20px;
      margin-right: 10px;
    }
  }
  // align-items: center;
}
</style>