<!--
 * @Author: yanchenxi
 * @Date: 2022-08-16 10:58:48
 * @LastEditTime: 2022-08-20 17:17:21
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\views\test\test4\Detail.vue
-->
<template>
  <div>
    <div class="commom-table">
      <div class="table-tip">当前订单： 66354782</div>
      <div class="table-box">
        <div class="form">
          <bfe-form ref="form" :model="form" label-width="auto">
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="所属合同编码">
                  <bfe-input v-model="form.a1" disabled></bfe-input>
                </bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="合同执行比例">
                  <bfe-input v-model="form.a1" disabled></bfe-input>
                </bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="订单金额">
                  <bfe-input v-model="form.a2" disabled></bfe-input>
                </bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="合同剩余金额">
                  <bfe-input v-model="form.a2" disabled></bfe-input>
                </bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="订单状态">
                  <bfe-input v-model="form.a3" disabled></bfe-input>
                </bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="医院名称">
                  <bfe-input v-model="form.a3" disabled></bfe-input>
                </bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="开始时间">
                  <bfe-input v-model="form.a2" disabled></bfe-input>
                </bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="医保局名称">
                  <bfe-input v-model="form.a2" disabled></bfe-input>
                </bfe-form-item>
              </div>
            </div>
          </bfe-form>
        </div>
        <div class="box">
          <bfe-steps :space="70" :active="2" direction="vertical" finish-status="success">
            <bfe-step description="合同已完成部分"></bfe-step>
            <bfe-step description="当前订单"></bfe-step>
            <bfe-step description="合同未开始部分"></bfe-step>
            <bfe-step title="合同结束"></bfe-step>
          </bfe-steps>
        </div>
        <div class="box-map">
          <china-map title="标段合同分布统计" :ptContracts="allData.ptContracts" />
        </div>
      </div>
    </div>
    <div class="commom-table test">
      <div class="table-tip">订单溯源</div>

      <div class="foot">
        <div class="foot-box">
          <div>
            <pre style="display:inline">时    间：</pre>
            <span>2022-08-12 12:12:11</span>
          </div>
          <div>
            <pre style="display:inline">类    型：</pre>
            <span>订单新建</span>
          </div>
          <div>
            <span>区块高度：</span>
            <span>234</span>
          </div>
          <div>
            <span>交易HASH：</span>
            <span>0x0b202556b2e77fc8d314d9ef808</span>
          </div>
        </div>
        <div class="foot-box" style="margin-left:1%">
          <div>
            <pre style="display:inline">时    间：</pre>
            <span>2022-08-12 12:12:11</span>
          </div>
          <div>
            <pre style="display:inline">类    型：</pre>
            <span>订单修改</span>
          </div>
          <div>
            <span>区块高度：</span>
            <span>284</span>
          </div>
          <div>
            <span>交易HASH：</span>
            <span>0x0b202556b2e77fc8d314d9ef223</span>
          </div>
        </div>
        <div class="foot-box" style="margin-left:1%">
          <div>
            <pre style="display:inline">时    间：</pre>
            <span>2022-08-12 12:12:11</span>
          </div>
          <div>
            <pre style="display:inline">类    型：</pre>
            <span>订单修改</span>
          </div>
          <div>
            <span>区块高度：</span>
            <span>344</span>
          </div>
          <div>
            <span>交易HASH：</span>
            <span>0x0b202556b2e77fc8d314d9ef991</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ChinaMap from './ChinaMap';
export default {
  components: { ChinaMap },
  data() {
    return {
      allData: { ptContracts: [] },
      form: {
        a1: '1262022036',
        a2: '成功',
        a3: '100.00元'
      }
    };
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },
    test() {}
  }
};
</script>

<style lang="less" scoped>
.tips {
  line-height: 60px;
  color: @color-ziti;
}
.table-box {
  position: relative;
}
.form {
  margin-top: 20px;
  width: 50%;
}
.box {
  width: 10%;
  // margin-top: 30px;
  position: absolute;
  right: 3%;
  top: 40px;
}
.test {
  margin-top: 20px;
}
.box-map {
  width: 50%;
  height: 300px;
  // height: 100%;
}
.table-box {
  display: flex;
}
.foot {
  display: flex;
  margin-top: 20px;
  // height: 200px;
}
.foot-box {
  background: url('../../../assets/imgs/表格bg.png') no-repeat;
  background-size: 100% 100%;
  height: 100%;
  width: 32%;
  // margin: 0px 0;
  div {
    color: #fff;
    margin: 20px;
  }
}
</style>