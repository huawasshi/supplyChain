<!--
 * @Author: yanchenxi
 * @Date: 2022-06-13 20:00:15
 * @LastEditTime: 2022-08-09 09:48:23
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\timeing\Detail.vue
-->
<template>
  <div>
    <bfe-form ref="form" :model="form" label-width="100px">
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="任务名称">
            <bfe-input v-model="form.a1" disabled></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="迁移任务名称">
            <bfe-select v-model="form.a2" placeholder="请选择" disabled>
              <bfe-option label="区域一" value="shanghai"></bfe-option>
              <bfe-option label="区域二" value="beijing"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="定时执行条件">
            <bfe-input v-model="form.a3" disabled></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="任务状态">
            <bfe-select v-model="form.a4" placeholder="请选择" disabled>
              <bfe-option label="区域一" value="shanghai"></bfe-option>
              <bfe-option label="区域二" value="beijing"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="创建人">
            <bfe-input v-model="form.a5" disabled></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="创建时间">
            <bfe-date-picker
              type="date"
              placeholder="选择时间"
              v-model="form.a6"
              style="width: 100%;"
              disabled
            ></bfe-date-picker>
          </bfe-form-item>
        </div>
      </div>
    </bfe-form>
    <div>
      <div class="tip">任务执行记录</div>
      <bfe-table :data="tableData" style="width: 100%">
        <bfe-table-column type="index" label="序号" width="100px"></bfe-table-column>
        <bfe-table-column prop="date" label="执行时间"></bfe-table-column>
        <bfe-table-column prop="name" label="执行结果"></bfe-table-column>
        <bfe-table-column prop="address" label="操作">
          <template>
            <bfe-button type="text" size="small" @click="goDetail">详情</bfe-button>
          </template>
        </bfe-table-column>
      </bfe-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        a1: '3123',
        a2: 'shanghai',
        a3: '1111',
        a4: 'shanghai',
        a5: '444',
        a6: new Date()
      },
      tableData: [
        {
          date: '2016-05-02 09:35:33',
          name: '失败'
        },
        {
          date: '2016-05-02 09:35:33',
          name: '失败'
        },
        {
          date: '2016-05-02 09:35:33',
          name: '失败'
        },
        {
          date: '2016-05-02 09:35:33',
          name: '失败'
        }
      ]
    };
  },
  mounted() {
    console.log('test'), (this.form = this.$route.query.data);
    this.form.a4 = '成功';
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },
    goDetail() {
      let row = {
        a1: '测试任务名称',
        a2: '测试数据源',
        a3: '目标数据源',
        a4: '2022-06-06 09:30:23',
        a5: '成功',
        a6: 'user',
        a7: '2016-05-02 10:11:23'
      };
      this.$router.push({
        path: '/dataTransfer/detail',
        query: {
          data: row
        }
      });
    }
  }
};
</script>

<style lang="less" scoped>
/deep/ .bfe-select {
  width: 100%;
}
.tip {
  margin: 20px;
  font-size: 14px;
  font-weight: bold;
}
</style>