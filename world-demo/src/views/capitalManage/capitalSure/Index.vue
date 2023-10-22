<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 20:57:51
 * @LastEditTime: 2022-08-22 11:23:17
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\views\capitalManage\capitalSure\Index.vue
-->
<template>
  <div>
    <search>
      <div slot="btn">
        <bfe-button type="primary" icon="search">查 询</bfe-button>
        <bfe-button icon="refresh">重 置</bfe-button>
      </div>
      <div slot="query">
        <bfe-form :inline="true" label-position="right" label-width="100px">
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="申请人">
                <bfe-input v-model="form.a1" placeholder="请输入申请人"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="药企名称">
                <bfe-input v-model="form.a2" placeholder="请输入药企名称"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="订单编号">
                <bfe-input v-model="form.a3" placeholder="请输入订单编号"></bfe-input>
              </bfe-form-item>
            </div>
          </div>
        </bfe-form>
      </div>
    </search>
    <div class="commom-table">
      <div class="table-tip">资金确认信息</div>
      <div class="tableContent">
        <bfe-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
          <bfe-table-column prop="a1" label="订单编号" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a2" label="订单名" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a3" label="医院名称" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a4" label="总金额/未结清(万元)" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a5" label="开始时间时间" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a6" label="订单支付比例" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a7" label="状态" show-overflow-tooltip align="center"></bfe-table-column>
        </bfe-table>

        <div class="table-page">
          <!-- TODO 根据返回数据再编写跳转分页逻辑 -->
          <bfe-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="9"
          ></bfe-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        a1: '',
        a2: '',
        a3: '',
        date1: '',
        a6: ''
      },
      currentPage: 1,
      tableData: [
        {
          a1: '20222255578971',
          a2: '采购单一',
          a3: '安徽省第一人民医院',
          a4: '221',
          a5: '2022-08-05 10:30:00',
          a6: '10%',
          a7: '已确认'
        },
        {
          a1: '20222255578972',
          a2: '采购单二',
          a3: '安徽省第一人民医院',
          a4: '62',
          a5: '2022-08-05 11:30:00',
          a6: '20%',
          a7: '已确认'
        },
        {
          a1: '20222255578973',
          a2: '采购单三',
          a3: '妇幼保健院',
          a4: '56',
          a5: '2022-08-05 12:30:00',
          a6: '45%',
          a7: '已确认'
        },
        {
          a1: '20222255578974',
          a2: '采购单四',
          a3: '安徽省第一人民医院',
          a4: '55',
          a5: '2022-08-05 13:30:00',
          a6: '76%',
          a7: '已确认'
        },
        {
          a1: '20222255578975',
          a2: '采购单五',
          a3: '妇幼保健院',
          a4: '225',
          a5: '2022-08-05 14:30:00',
          a6: '43%',
          a7: '已确认'
        },
        {
          a1: '20222255578976',
          a2: '采购单六',
          a3: '安徽省第一人民医院',
          a4: '9',
          a5: '2022-08-05 15:30:00',
          a6: '15%',
          a7: '已确认'
        },
        {
          a1: '20222255578977',
          a2: '采购单七',
          a3: '省立',
          a4: '87',
          a5: '2022-08-05 16:30:00',
          a6: '19%',
          a7: '已确认'
        },
        {
          a1: '20222255578978',
          a2: '采购单八',
          a3: '省立',
          a4: '32',
          a5: '2022-08-05 17:30:00',
          a6: '17%',
          a7: '已确认'
        },
        {
          a1: '20222255578979',
          a2: '采购单九',
          a3: '安徽省第一人民医院',
          a4: '227',
          a5: '2022-08-05 18:30:00',
          a6: '10%',
          a7: '已确认'
        }
      ]
    };
  },
  methods: {
    handleSizeChange(val) {
      console.log(val);
    },
    handleCurrentChange(val) {
      console.log(val);
    },
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

<style lang="less" scoped>
.line {
  text-align: center;
}
</style>
