<!--
 * @Author: yanchenxi
 * @Date: 2021-11-19 09:44:43
 * @LastEditTime: 2022-08-16 18:47:58
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \yanshi\src\views\map\components\Table1.vue
-->
<template>
  <div class="com-container">
    <div class="table-title">{{title}}</div>
    <bfe-table
      :data="[]"
      style="position:absolute;top:23% ;z-index:100000;width:92%;left:4%"
      empty-text="  "
      class="test1"
    >
      <bfe-table-column prop="tranTime" label="日期" align="center" show-overflow-tooltip></bfe-table-column>
      <bfe-table-column prop="txIndex" label="交易索引" align="center" show-overflow-tooltip></bfe-table-column>
      <bfe-table-column prop="blockNumber" label="所属区块" align="center" show-overflow-tooltip></bfe-table-column>
      <bfe-table-column prop="txHash" label="交易Hash" align="center" show-overflow-tooltip></bfe-table-column>
    </bfe-table>
    <div class="table-content">
      <vue-seamless-scroll :data="lastTrans" :class-option="defaultOption" class="test">
        <bfe-table :data="lastTrans" style="width: 100%" :row-class-name="tableRowClassName">
          <bfe-table-column prop="tranTime" label="日期" align="center" show-overflow-tooltip></bfe-table-column>
          <bfe-table-column prop="txIndex" label="交易索引" align="center" show-overflow-tooltip></bfe-table-column>
          <bfe-table-column prop="blockNumber" label="所属区块" align="center" show-overflow-tooltip></bfe-table-column>
          <bfe-table-column prop="txHash" label="交易Hash" align="center" show-overflow-tooltip></bfe-table-column>
        </bfe-table>
      </vue-seamless-scroll>
    </div>
  </div>
</template>

<script>
import vueSeamlessScroll from 'vue-seamless-scroll';
export default {
  components: { vueSeamlessScroll },
  props: {
    title: {
      type: String,
      default: ''
    },
    lastTrans: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      tableData: []
    };
  },
  computed: {
    defaultOption() {
      return {
        step: 0.3,
        limitMoveNum: 2,
        hoverStop: true,
        direction: 1,
        openWatch: true,
        singleHeight: 0,
        singleWidth: 0,
        waitTime: 1000
      };
    }
  },
  methods: {
    tableRowClassName(row, index) {
      if (index % 2 === 0) {
        return 'positive-row';
      } else {
        return 'info-row';
      }
    }
  }
};
</script>

<style scoped >
.table-title {
  background: url('../../../assets/imgs/标题bg.png') no-repeat;
  background-size: 100% 100%;
  height: 12%;
  width: 70%;
  position: absolute;
  left: 15%;
  top: 7%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
}
.table-content {
  background: url('../../../assets/imgs/表格bg.png') no-repeat;
  background-size: 100% 100%;
  height: 73%;
  position: absolute;
  width: 96%;
  top: 19%;
  margin: 2%;
  overflow: hidden;
  padding: 2%;
}
.test {
  margin-top: 33px;
}
</style>
