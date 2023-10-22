<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 20:57:51
 * @LastEditTime: 2022-08-20 17:25:05
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\views\capitalManage\capitalApprove\Index.vue
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
              <bfe-form-item label="药企名称">
                <bfe-select v-model="form.a1" placeholder="请选择医院" style="width:100%">
                  <bfe-option label="医院一" value="1"></bfe-option>
                  <bfe-option label="医院二" value="2"></bfe-option>
                  <bfe-option label="医院三" value="3"></bfe-option>
                  <bfe-option label="医院四" value="4"></bfe-option>
                </bfe-select>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="合同编号">
                <bfe-input v-model="form.a2" placeholder="请输入"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="申请金额">
                <div class="col-md-5">
                  <bfe-input placeholder="起始金额(元)"></bfe-input>
                </div>
                <div class="col-md-1 line">至</div>
                <div class="col-md-5">
                  <bfe-input placeholder="终止金额(元)"></bfe-input>
                </div>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="申请时间">
                <bfe-date-picker
                  type="daterange"
                  placeholder="开始日期 ~ 结束日期 "
                  v-model="form.date1"
                  style="width: 100%;"
                ></bfe-date-picker>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="审批状态">
                <bfe-select v-model="form.a6" placeholder="请选择" style="width:100%">
                  <bfe-option label="待审批" value="1"></bfe-option>
                  <bfe-option label="全部" value="2"></bfe-option>
                </bfe-select>
              </bfe-form-item>
            </div>
          </div>
        </bfe-form>
      </div>
    </search>
    <div class="commom-table">
      <div class="table-tip">资金审批信息</div>
      <div class="tableContent">
        <bfe-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
          <bfe-table-column prop="a1" label="药企名称" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a2" label="合同编号" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a3" label="申请金额(万元)" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a4" label="医疗机构" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a5" label="申请时间" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a6" label="状态" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column label="操作" show-overflow-tooltip align="center">
            <template>
              <bfe-button type="text" size="small">
                <span style="color:#dcac45">订单跟踪</span>
              </bfe-button>

              <bfe-button type="text" size="small">
                <span style="color:#0487ed">查询</span>
              </bfe-button>

              <bfe-button type="text" size="small">
                <span style="color:#42e2fa">刷新</span>
              </bfe-button>
            </template>
          </bfe-table-column>
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
        date1: '',
        a6: ''
      },
      currentPage: 1,
      tableData: [
        {
          a1: '药企一',
          a2: '20222255578910',
          a3: '22.5',
          a4: '省立医院',
          a5: '2022-08-05 9:30:00',
          a6: '待审批'
        },
        {
          a1: '药企二',
          a2: '20222255578972',
          a3: '12',
          a4: '妇幼保健院',
          a5: '2022-08-05 10:30:00',
          a6: '待审批'
        },
        {
          a1: '药企三',
          a2: '20222255578973',
          a3: '99',
          a4: '安徽省第一人民医院',
          a5: '2022-08-05 10:30:00',
          a6: '待审批'
        },
        {
          a1: '药企四',
          a2: '20222255578974',
          a3: '22.5',
          a4: '省立医院',
          a5: '2022-08-05 11:30:00',
          a6: '待审批'
        },
        {
          a1: '药企五',
          a2: '20222255578975',
          a3: '88',
          a4: '安徽省第一人民医院',
          a5: '2022-08-05 12:30:00',
          a6: '待审批'
        },
        {
          a1: '药企六',
          a2: '20222255578976',
          a3: '221',
          a4: '安徽省第一人民医院',
          a5: '2022-08-05 13:30:00',
          a6: '待审批'
        },
        {
          a1: '药企七',
          a2: '20222255578977',
          a3: '232.5',
          a4: '安徽省第一人民医院',
          a5: '2022-08-05 14:30:00',
          a6: '待审批'
        },
        {
          a1: '药企八',
          a2: '20222255578978',
          a3: '233',
          a4: '安徽省第一人民医院',
          a5: '2022-08-05 15:30:00',
          a6: '待审批'
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
