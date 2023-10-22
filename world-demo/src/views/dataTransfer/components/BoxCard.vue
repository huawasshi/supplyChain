<!--
 * @Author: yanchenxi
 * @Date: 2022-08-02 09:52:02
 * @LastEditTime: 2022-08-09 09:37:18
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\dataTransfer\components\BoxCard.vue
-->
<template>
  <bfe-card class="box-card">
    <div slot="header" class="clearfix">
      <span style="line-height: 36px;">数据迁移详情列表</span>
    </div>
    <div class="text item">
      <bfe-collapse>
        <bfe-collapse-item title="迁移成功+验证通过" name="1">
          <div>
            <bfe-select v-model="value" placeholder="请选择">
              <bfe-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></bfe-option>
            </bfe-select>
          </div>
          <bfe-form ref="form">
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="表名：">{{detail.name}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="总条数：">{{detail.total}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="启动时间：">{{detail.startTime}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="耗时：">{{detail.zhixingTime}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="源库系统验证结果：">{{detail.aa}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="目标库验证结果：">{{detail.bb}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="源库自定义验证结果：">{{detail.cc}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="目标库自定义验证结果：">{{detail.dd}}</bfe-form-item>
              </div>
            </div>
          </bfe-form>
        </bfe-collapse-item>
        <bfe-collapse-item name="2">
          <template slot="title">
            <span style="color:red;font-weight:bold">迁移成功+验证失败</span>
          </template>
          <div>
            <bfe-select v-model="value2" placeholder="请选择">
              <bfe-option
                v-for="item in options2"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></bfe-option>
            </bfe-select>
          </div>
          <bfe-form ref="form2">
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="启动时间：">{{detail2.startTime}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="耗时：">{{detail2.zhixingTime}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="源库系统验证结果：">{{detail2.aa}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="目标库验证结果：" style="color:red">{{detail2.bb}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="源库自定义验证结果：">{{detail2.cc}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="目标库自定义验证结果：" style="color:red">{{detail2.dd}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="总条数：">{{detail2.total}}</bfe-form-item>
              </div>
            </div>
          </bfe-form>
        </bfe-collapse-item>
        <bfe-collapse-item title="迁移中" name="3">
          <div>
            <bfe-select v-model="value3" placeholder="请选择">
              <bfe-option
                v-for="item in options3"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></bfe-option>
            </bfe-select>
          </div>
          <bfe-form ref="form3">
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="总条数：">{{detail3.total}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="启动时间：">{{detail3.startTime}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="成功条数：">{{detail3.suc}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="执行耗时：">{{detail3.zhixingTime}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="预计耗时：">{{detail3.yjtime}}</bfe-form-item>
              </div>
            </div>
          </bfe-form>
        </bfe-collapse-item>
        <bfe-collapse-item name="4">
          <template slot="title">
            <span style="color:red;font-weight:bold">迁移失败</span>
          </template>
          <div>
            <bfe-select v-model="value4" placeholder="请选择">
              <bfe-option
                v-for="item in options4"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></bfe-option>
            </bfe-select>
          </div>
          <bfe-form ref="form4">
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="总条数：">{{detail4.total}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="成功条数：">{{detail4.suc}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-6">
                <bfe-form-item label="执行时间：">{{detail4.startTime}}</bfe-form-item>
              </div>
              <div class="col col-md-6">
                <bfe-form-item label="耗时：">{{detail4.zhixingTime}}</bfe-form-item>
              </div>
            </div>
            <div class="row">
              <div class="col col-md-12" style="color:red">
                <bfe-form-item label="失败原因：">{{detail4.err}}</bfe-form-item>
              </div>
            </div>
          </bfe-form>
        </bfe-collapse-item>
      </bfe-collapse>
    </div>
  </bfe-card>
</template>

<script>
import datas from './boxcard.json';
export default {
  data() {
    return {
      value: 'aptacccusinfo',
      value2: 'ibvoprhis',
      value3: 'aptjrntranhis',
      value4: 'ha_bntype',
      options: [],
      options2: [],
      options3: [],
      options4: []
    };
  },
  computed: {
    detail() {
      return datas['qianyiSuc-yanzSuc'][this.value] || {};
    },
    detail2() {
      return datas['qianyiSuc-yanzError'][this.value2] || {};
    },
    detail3() {
      return datas['qianyiIng'][this.value3] || {};
    },
    detail4() {
      return datas['qianyiError'][this.value4] || {};
    }
  },
  mounted() {
    this.options = Object.keys(datas['qianyiSuc-yanzSuc']).map(v => ({
      label: v,
      value: v
    }));
    this.options2 = Object.keys(datas['qianyiSuc-yanzError']).map(v => ({
      label: v,
      value: v
    }));
    this.options4 = Object.keys(datas['qianyiError']).map(v => ({
      label: v,
      value: v
    }));
    this.options3 = Object.keys(datas['qianyiIng']).map(v => ({
      label: v,
      value: v
    }));
  }
};
</script>

<style lang="less" scoped>
.row {
  font-weight: bold;
}
</style>