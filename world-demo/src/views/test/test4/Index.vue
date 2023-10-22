<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 20:57:51
 * @LastEditTime: 2022-08-20 17:09:10
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\views\test\test4\Index.vue
-->
<template>
  <div>
    <search>
      <div slot="btn">
        <bfe-button type="primary" icon="search">查 询</bfe-button>
        <bfe-button icon="refresh">重 置</bfe-button>
      </div>
      <div slot="query">
        <bfe-form :inline="true">
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="医院名称">
                <bfe-select v-model="form.a1" placeholder="请选择医院" style="width:100%">
                  <bfe-option label="医院一" value="1"></bfe-option>
                  <bfe-option label="医院二" value="2"></bfe-option>
                  <bfe-option label="医院三" value="3"></bfe-option>
                  <bfe-option label="医院四" value="4"></bfe-option>
                </bfe-select>
              </bfe-form-item>
            </div>
          </div>
        </bfe-form>
      </div>
    </search>
    <div class="commom-table">
      <div class="table-tip">订单跟踪信息</div>
      <div class="tableContent">
        <bfe-table :data="tableData" style="width: 100%" :row-class-name="tableRowClassName">
          <bfe-table-column type="index" label="序号" align="center"></bfe-table-column>
          <bfe-table-column prop="a1" label="订单编号" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a2" label="订单名" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a3" label="医院名称" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a4" label="总金额/未结清(万元)" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a5" label="开始时间" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column prop="a6" label="订单状态" show-overflow-tooltip align="center"></bfe-table-column>
          <bfe-table-column label="操作" show-overflow-tooltip align="center">
            <template>
              <bfe-button type="text" size="small" @click="goDetail">
                <span style="color:#dcac45">订单跟踪详情</span>
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
        a2: ''
      },
      currentPage: 1,
      tableData: [
        {
          a1: '1052002514',
          a2: '采购单',
          a3: '安医',
          a4: '100.00/100.00',
          a5: '2022-08-05',
          a6: '未审核'
        },
        {
          a1: '1052002523',
          a2: '采购单二',
          a3: '省立医院',
          a4: '100.00/100.00',
          a5: '2022-08-06',
          a6: '未审核'
        },
        {
          a1: '1052002312',
          a2: '采购单三',
          a3: '安医',
          a4: '100.00/100.00',
          a5: '2022-08-07',
          a6: '未审核'
        },
        {
          a1: '1052002543',
          a2: '采购单四',
          a3: '儿童医院',
          a4: '100.00/100.00',
          a5: '2022-08-08',
          a6: '未审核'
        },
        {
          a1: '1052002876',
          a2: '采购单五',
          a3: '安医',
          a4: '100.00/100.00',
          a5: '2022-08-09',
          a6: '未审核'
        },
        {
          a1: '1052002098',
          a2: '采购单六',
          a3: '妇幼保健院',
          a4: '100.00/100.00',
          a5: '2022-08-10',
          a6: '未审核'
        },
        {
          a1: '1052002984',
          a2: '采购单七',
          a3: '安医',
          a4: '100.00/100.00',
          a5: '2022-08-11',
          a6: '未审核'
        },
        {
          a1: '1052002398',
          a2: '采购单八',
          a3: '安医附二院',
          a4: '100.00/100.00',
          a5: '2022-08-12',
          a6: '未审核'
        },
        {
          a1: '1052002543',
          a2: '采购单九',
          a3: '安医',
          a4: '100.00/100.00',
          a5: '2022-08-05',
          a6: '未审核'
        }
      ]
    };
  },
  methods: {
    goDetail() {
      this.$router.push({ name: 'Test4Detail' });
    },
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
</style>
