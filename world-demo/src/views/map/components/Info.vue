<!--
 * @Author: yanchenxi
 * @Date: 2021-08-16 10:24:21
 * @LastEditTime: 2022-08-19 13:33:15
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \yanshi\src\views\map\components\Info.vue
-->
<template>
  <section class="info-warp">
    <div class="info-top box">
      <div class="num">
        <div class="item-box">
          <div>订单总数</div>
          <div>{{blockOverview.totalTranNum}}</div>
        </div>
        <div class="item-box">
          <div>块高</div>
          <div>{{blockOverview.blockHeight}}</div>
        </div>
        <div class="item-box">
          <div>节点数</div>
          <div>{{blockOverview.nodeNum}}</div>
        </div>
        <div class="item-box">
          <div>已加入机构数</div>
          <div>{{29}}</div>
        </div>
      </div>
      <div class="num">
        <div class="item-box">
          <div>项目类型</div>
          <div>{{blockOverview.projectType}}</div>
        </div>
        <div class="item-box">
          <div>进行中单数</div>
          <div>{{blockOverview.ptContractNum}}</div>
        </div>
        <div class="item-box">
          <div>运行状态</div>
          <div>{{blockOverview.runStatus}}</div>
        </div>
        <div class="item-box">
          <div>网络类型</div>
          <div>{{blockOverview.ptExpndtrplnNum}}</div>
        </div>
        <!-- <div class="item-box">
          <div>上月交易</div>
          <div>{{blockOverview.lastMonthTranNum}}</div>
        </div>
        <div class="item-box">
          <div>SDK类型</div>
          <div>{{blockOverview.SDKType}}</div>
        </div>-->
      </div>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    blockOverview: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },
  data() {
    return {};
  }
};
</script>

<style  scoped>
.info-warp {
  height: 100%;
  width: 100%;
  padding: 20px 0;
}
.info-top {
  width: 100%;
  height: 100%;
}
.num {
  display: flex;
  height: 50%;
}
.num .item-box {
  width: 25%;
  text-align: center;
  background: #0f1c48;
}
.item-box div:nth-child(1) {
  height: 60%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px;
  color: #42e2fa;
}
.item-box div:nth-child(2) {
  font-size: 16px;
}
.num .item-box:nth-child(even) {
  margin: 0 20px;
}
.num:nth-child(1) {
  padding-bottom: 10px;
}
.num:nth-child(2) {
  padding-top: 10px;
}
</style>
