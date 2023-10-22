<!--
 * @Author: yanchenxi
 * @Date: 2022-08-16 10:58:48
 * @LastEditTime: 2022-08-18 14:55:35
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\views\test\test2\Index.vue
-->
<template>
  <div>
    <div class="commom-table">
      <div class="table-tip">订单新建表单</div>
      <div class="form">
        <bfe-form ref="form" :model="form" label-width="auto">
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="订单编号">
                <bfe-input v-model="form.a1" placeholder="请输入订单名" disabled></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="所属合同编号">
                <bfe-select v-model="form.a16" placeholder="请选择所属合同编号" style="width:100%">
                  <bfe-option label="编号一" value="shanghai"></bfe-option>
                  <bfe-option label="编号二" value="beijing"></bfe-option>
                </bfe-select>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="订单名">
                <bfe-input v-model="form.a2" placeholder="请输入订单名"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="监管账户">
                <bfe-input v-model="form.a3" placeholder="请输入监管账户"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="合同剩余金额">
                <bfe-input v-model="form.a4" placeholder="请输入合同剩余金额"></bfe-input>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="药企名称">
                <bfe-input v-model="form.a5" placeholder="请输入药企名称"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="监管账户开户行">
                <bfe-input v-model="form.a6" placeholder="请输入"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="合同执行比例">
                <bfe-input v-model="form.a7" placeholder="请输入合同执行比例"></bfe-input>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="医院名称">
                <bfe-select v-model="form.a8" placeholder="请选择医院名称" style="width:100%">
                  <bfe-option label="医院一" value="shanghai"></bfe-option>
                  <bfe-option label="医院二" value="beijing"></bfe-option>
                </bfe-select>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="收款账户">
                <bfe-input v-model="form.a9" placeholder="请输入收款账户"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="合同生效日期">
                <bfe-date-picker
                  v-model="form.a10"
                  type="date"
                  placeholder="选择日期"
                  style="width: 100%"
                ></bfe-date-picker>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="医保局名称">
                <bfe-input v-model="form.a11" placeholder="请输入医保局名称"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="收款方名称">
                <bfe-input v-model="form.a12" placeholder="请输入收款方名称"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="收款方组织机构代码">
                <bfe-input v-model="form.a13" placeholder="请输入"></bfe-input>
              </bfe-form-item>
            </div>
          </div>
          <div class="row">
            <div class="col col-md-4">
              <bfe-form-item label="合同总金额">
                <bfe-input v-model="form.a14" placeholder="请输入合同总金额"></bfe-input>
              </bfe-form-item>
            </div>
            <div class="col col-md-4">
              <bfe-form-item label="收款账户开户行">
                <bfe-input v-model="form.a15" placeholder="请输入收款账户开户行"></bfe-input>
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
                      <bfe-button size="small" type="primary">点击上传</bfe-button>
                    </bfe-upload>
                  </template>
                </bfe-input>
              </bfe-form-item>
            </div>
          </div>

          <bfe-form-item></bfe-form-item>
          <div style="text-align:center">
            <bfe-button @click="onSubmit">取 消</bfe-button>
            <bfe-button type="primary">保 存</bfe-button>
            <bfe-button>提 交</bfe-button>
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
        a1: '1262022036',
        a2: '',
        a3: '',
        a4: '',
        a5: '',
        a6: '',
        a7: '',
        a8: '',
        a9: '',
        a10: '',
        a11: '',
        a12: '',
        a13: '',
        a14: '',
        a15: '',
        a16: ''
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
.form {
  margin-top: 20px;
}
</style>