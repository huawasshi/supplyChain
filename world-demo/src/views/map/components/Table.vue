<!--
 * @Author: yanchenxi
 * @Date: 2021-11-19 09:44:43
 * @LastEditTime: 2022-08-16 18:47:47
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \yanshi\src\views\map\components\Table.vue
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
      <bfe-table-column prop="nodeName" align="center" label="节点"></bfe-table-column>
      <bfe-table-column prop="nodeStatus" align="center" label="状态"></bfe-table-column>
    </bfe-table>
    <div class="table-content">
      <vue-seamless-scroll :data="nodeStatuses" :class-option="defaultOption" class="test">
        <bfe-table :data="nodeStatuses" style="width: 100%" :row-class-name="tableRowClassName">
          <bfe-table-column prop="nodeName" align="center"></bfe-table-column>
          <bfe-table-column prop="nodeStatus" align="center">
            <template slot-scope="scope">
              <span :class="scope.row.nodeStatus==='正常'?'red':'blue'">{{scope.row.nodeStatus}}</span>
            </template>
          </bfe-table-column>
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
    nodeStatuses: {
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
.bfe-table__empty-block {
  display: none;
}
th {
  display: none;
}
.red {
  color: #dcac45;
}
.blue {
  color: blue;
}
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
