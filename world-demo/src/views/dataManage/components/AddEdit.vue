<!--
 * @Author: yanchenxi
 * @Date: 2022-06-13 16:55:57
 * @LastEditTime: 2022-08-01 16:05:12
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\dataManage\components\AddEdit.vue
-->
<template>
  <div>
    <bfe-dialog :title="title" :visible.sync="isShow" size="tiny" :before-close="channel">
      <bfe-form ref="form" :model="form" label-width="auto">
        <div class="row">
          <div class="col col-md-6">
            <bfe-form-item label="数据源名称">
              <bfe-input v-model="form.a2" :disabled="title==='详情'?true:false"></bfe-input>
            </bfe-form-item>
          </div>
          <div class="col col-md-6">
            <bfe-form-item label="数据源类型">
              <bfe-select v-model="form.a1" placeholder="请选择" :disabled="title==='详情'?true:false">
                <bfe-option label="tdsql" value="tdsql"></bfe-option>
                <bfe-option label="oracle" value="oracle"></bfe-option>
                <bfe-option label="mysql" value="mysql"></bfe-option>
                <bfe-option label="postgresql" value="postgresql"></bfe-option>
                <bfe-option label="sqlserver" value="sqlserver"></bfe-option>
                <bfe-option label="hive" value="hive"></bfe-option>
                <bfe-option label="hbase" value="hbase"></bfe-option>
                <bfe-option label="mongodb" value="mongodb"></bfe-option>
                <bfe-option label="clickhouse" value="clickhouse"></bfe-option>
              </bfe-select>
            </bfe-form-item>
          </div>
        </div>

        <div class="row">
          <div class="col col-md-6">
            <bfe-form-item label="IP地址">
              <bfe-input v-model="form.a3" :disabled="title==='详情'?true:false"></bfe-input>
            </bfe-form-item>
          </div>
          <div class="col col-md-6">
            <bfe-form-item label="端口">
              <bfe-input v-model="form.a4" :disabled="title==='详情'?true:false"></bfe-input>
            </bfe-form-item>
          </div>
        </div>

        <div class="row">
          <div class="col col-md-6">
            <bfe-form-item label="数据库名/SID">
              <bfe-input v-model="form.a5" :disabled="title==='详情'?true:false"></bfe-input>
            </bfe-form-item>
          </div>
          <div class="col col-md-6" v-if="title==='详情'">
            <bfe-form-item label="链接状态">
              <bfe-input v-model="form.a7" :disabled="title==='详情'?true:false"></bfe-input>
            </bfe-form-item>
          </div>
        </div>

        <div class="row">
          <div class="col col-md-6">
            <bfe-form-item label="用户名">
              <bfe-input v-model="form.a6" :disabled="title==='详情'?true:false"></bfe-input>
            </bfe-form-item>
          </div>
          <div class="col col-md-6">
            <bfe-form-item label="密码">
              <bfe-input v-model="form.a8" :disabled="title==='详情'?true:false" type="password"></bfe-input>
            </bfe-form-item>
          </div>
        </div>
      </bfe-form>
      <span slot="footer" class="dialog-footer">
        <bfe-button @click="channel">取 消</bfe-button>
        <bfe-button type="primary" @click="show">确 认</bfe-button>
        <bfe-button type="primary" @click="testconnect">测试链接</bfe-button>
      </span>
    </bfe-dialog>
  </div>
</template>

<script>
export default {
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    dialogData: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      title: '新增',
      isShow: this.dialogVisible,
      form: {
        a1: '',
        a2: '',
        a3: '',
        a4: '',
        a5: '',
        a6: '',
        a7: '',
        a8: ''
      }
    };
  },
  mounted() {
    this.form = this.dialogData.row;
    if (this.dialogData.type === 'add') {
      this.title = '新增';
    } else if (this.dialogData.type === 'edit') {
      this.title = '修改';
    } else if (this.dialogData.type === 'detail') {
      this.title = '详情';
    }

    console.log(this.form);
  },
  methods: {
    channel() {
      this.isShow = false;
      this.$emit('update:dialogVisible', false);
    },
    show() {
      this.isShow = true;
      this.$emit('update:dialogVisible', false);
    },
    testconnect() {
      this.$message({
        type: 'info',
        message: '测试链接成功'
      });
    },
    onSubmit() {
      console.log('submit!');
    }
  }
};
</script>

<style lang="less" scoped>
.dialog-footer {
  text-align: center;
  width: 100%;
  display: inline-block;
}
</style>