<!--
 * @Author: yanchenxi
 * @Date: 2022-06-13 16:55:57
 * @LastEditTime: 2022-08-10 09:23:36
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\timeing\components\AddEdit.vue
-->
<template>
  <div>
    <bfe-dialog
      :title="dialogData.type==='add'?'新增':'编辑'"
      :visible.sync="isShow"
      size="tiny"
      :before-close="channel"
    >
      <bfe-form ref="form" :model="form" label-width="auto">
        <div class="row">
          <div class="col col-md-6">
            <bfe-form-item label="任务名称">
              <bfe-input v-model="form.a1"></bfe-input>
            </bfe-form-item>
          </div>
          <!-- <div class="col col-md-6">
            <bfe-form-item label="创建人">
              <bfe-input v-model="form.a5"></bfe-input>
            </bfe-form-item>
          </div>-->
          <div class="col col-md-6">
            <bfe-form-item label="定时执行条件">
              <bfe-input v-model="form.a3"></bfe-input>
            </bfe-form-item>
          </div>
        </div>
        <div class="row">
          <div class="col col-md-6">
            <bfe-form-item label="任务状态">
              <bfe-select v-model="form.a4" placeholder="请选择">
                <bfe-option label="启用" value="1"></bfe-option>
                <bfe-option label="停用" value="2"></bfe-option>
              </bfe-select>
            </bfe-form-item>
          </div>
          <div class="col col-md-6">
            <bfe-form-item label="迁移任务名称">
              <bfe-select v-model="form.a2" placeholder="请选择">
                <bfe-option label="任务一" value="1"></bfe-option>
                <bfe-option label="任务二" value="2"></bfe-option>
                <bfe-option label="任务三" value="3"></bfe-option>
                <bfe-option label="任务四" value="4"></bfe-option>
                <bfe-option label="任务五" value="5"></bfe-option>
                <bfe-option label="任务六" value="6"></bfe-option>
              </bfe-select>
            </bfe-form-item>
          </div>
        </div>
        <div class="row">
          <!-- <div class="col col-md-6">
            <bfe-form-item label="创建时间">
              <bfe-date-picker
                type="date"
                placeholder="选择日期"
                v-model="form.a6"
                style="width: 100%;"
              ></bfe-date-picker>
            </bfe-form-item>
          </div>-->
        </div>
      </bfe-form>
      <span slot="footer" class="dialog-footer">
        <bfe-button @click="channel">取 消</bfe-button>
        <bfe-button type="primary" @click="show">确 定</bfe-button>
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
      isShow: this.dialogVisible,
      form: {
        a1: '',
        a2: '',
        a3: '',
        a4: '',
        a5: '艾斯',
        a6: ''
      }
    };
  },
  mounted() {
    if (this.dialogData.type === 'add') {
      this.title = '新增';
    } else if (this.dialogData.type === 'edit') {
      console.log(this.dialogData);
      this.title = '修改';
      this.form = {
        a1: '测试任务名称',
        a2: '1',
        a3: this.dialogData.row.a3,
        a4: this.dialogData.row.a4 ? '1' : '2',
        a5: '艾斯',
        a6: ''
      };
    }
  },
  methods: {
    channel() {
      this.isShow = false;
      this.$emit('update:dialogVisible', false);
    },
    show() {
      this.isShow = true;
      console.log(this.dialogData);
      this.$emit('update:dialogVisible', false);
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