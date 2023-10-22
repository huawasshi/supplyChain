<!--
 * @Author: yanchenxi
 * @Date: 2022-08-16 10:58:48
 * @LastEditTime: 2022-08-18 10:52:29
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\views\capitalManage\capitalApply\Index.vue
-->
<template>
  <div>
    <div class="commom-table">
      <div class="table-tip">资金申请表单</div>
      <div class="form">
        <bfe-form ref="form" :model="form" label-width="auto">
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="药企名称">
                <bfe-input v-model="form.a3" placeholder="请输入药企名称"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="申请人">
                <bfe-input v-model="form.a2" placeholder="请输入申请人"></bfe-input>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="医院名称">
                <bfe-select v-model="form.region" placeholder="请选择医院名称" style="width:100%">
                  <bfe-option label="医院一" value="shanghai"></bfe-option>
                  <bfe-option label="医院二" value="beijing"></bfe-option>
                </bfe-select>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="订单编号">
                <bfe-input v-model="form.desc" placeholder="请输入订单编号"></bfe-input>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="合同收款方账号">
                <bfe-input v-model="form.a1" placeholder="请输入订单编号"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="申请金额">
                <bfe-input v-model="form.a4" placeholder="请输入申请金额"></bfe-input>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="合同收款方开户行">
                <bfe-input v-model="form.a5" placeholder="请输入合同收款方开户行"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="合同总金额">
                <bfe-input v-model="form.a6" placeholder="请输入总金额"></bfe-input>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="收款方名称">
                <bfe-input v-model="form.a7" placeholder="请输入收款方名称"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="合同未结清金额">
                <bfe-input v-model="form.a8" placeholder="请输入合同未结清金额"></bfe-input>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="添加订单链接">
                <bfe-input v-model="form.a9" placeholder="添加订单链接"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="合同附件">
                <bfe-input placeholder="请输入">
                  <template slot="append">
                    <bfe-upload
                      name="file1"
                      class="upload-demo"
                      action="#"
                      multiple
                      :limit="3"
                      :show-file-list="false"
                      :auto-upload="false"
                    >
                      <bfe-button size="small" type="primary">提 交</bfe-button>
                    </bfe-upload>
                  </template>
                </bfe-input>
              </bfe-form-item>
            </div>
          </div>
          <div style="text-align:center">
            <bfe-button type="primary" @click="onSubmit">保 存</bfe-button>
            <bfe-button>提交审核</bfe-button>
          </div>
        </bfe-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: '',
        a1: '',
        a2: '',
        a3: '',
        a4: '',
        a5: '',
        a6: '',
        a7: '',
        a8: '',
        a9: ''
      }
    };
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    }
  }
};
</script>

<style lang="less" scoped>
.tips {
  line-height: 60px;
  color: @color-ziti;
}
.form {
  margin-top: 20px;
}
</style>