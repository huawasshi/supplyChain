<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 20:57:51
 * @LastEditTime: 2023-10-21 13:53:21
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\src\views\info\Index.vue
-->
<template>
  <div class="content">
    <div class="content-top"></div>
    <div class="content-main">
      <el-form ref="form" :model="form" label-width="auto">
        <div v-if="userInfo.registration_type==='生产商'">
          <el-row>
            <el-col :span="span">
              <el-form-item label="Traceability ID">
                <el-input v-model="form.traceabilityId"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="Trade name">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="span">
              <el-form-item label="Date of manufacture">
                <el-date-picker
                  v-model="form.manufactureDate"
                  type="date"
                  placeholder="Select date"
                  style="width: 100%"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="Manufacturer">
                <el-input v-model="form.manufactor"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="span">
              <el-form-item label="Manufacture’s phone number">
                <el-input v-model="form.telephone"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="Responsible person">
                <el-input v-model="form.person"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="span">
              <el-form-item label="shelf life">
                <el-input v-model="form.validityDate"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="batch number">
                <el-input v-model="form.batchNo"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="span">
              <el-form-item label="quantity">
                <el-input v-model="form.amount"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="country of origin">
                <el-input v-model="form.area"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </div>

        <div v-else>
          <el-row>
            <el-col :span="span">
              <el-form-item label="Manufacturer’s responsible person">
                <el-input v-model="form.companyPerson"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="Manufacturer’s phone number">
                <el-input v-model="form.tel"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="span">
              <el-form-item label="corporate name">
                <el-input v-model="form.company"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="the date of issuance">
                <el-date-picker
                  v-model="form.deliveryDate"
                  type="date"
                  placeholder="Select date"
                  style="width: 100%"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="span">
              <el-form-item label="waybill number">
                <el-input v-model="form.courierNumber"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="batch number">
                <el-input v-model="form.batchNo"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="span">
              <el-form-item label="Traceability ID">
                <el-input v-model="form.traceabilityId"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="span">
              <el-form-item label="purchase quantity">
                <el-input v-model="form.amount"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </div>

        <el-form-item class="bottom">
          <el-button type="primary" @click="onSub">commit</el-button>
          <el-button @click="goBack">return</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { fabricTraceUpdata } from '@apis/user.js';
export default {
  data() {
    return {
      span: 8,
      form: {
        traceabilityId: '',
        name: '',
        manufactureDate: '',
        manufactor: '',
        telephone: '',
        person: '',
        validityDate: '',
        batchNo: '',
        companyPerson: '',
        tel: '',
        company: '',
        deliveryDate: '',
        courierNumber: ''
      }
    };
  },
  computed: {
    ...mapGetters(['userInfo'])
  },
  methods: {
    goBack() {
      this.$router.push({ path: '/dashbard/index' });
    },
    onSub() {
      console.log(fabricTraceUpdata);
      fabricTraceUpdata(this.form).then(res => {
        console.log(res);
      });
    }
  }
};
</script>

<style lang="less" scoped>
.content {
  margin: 0 50px;
  // background: red;
  height: calc(100vh - 60px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  &-top {
    background-image: url('../../assets/info/title.png');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    width: 70%;
    height: 35px;
    margin-bottom: 50px;
  }
  &-main {
    display: flex;
    width: 100%;
    justify-content: center;
    align-items: center;
    background-image: url('../../assets/info/content.png');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    height: 600px;
    /deep/ .el-row {
      display: flex;
      justify-content: space-evenly;
    }
    /deep/ .el-form {
      width: 100% !important;
    }
    /deep/ .el-input__inner {
      width: 100%;
      height: 50px;
      // border-radius: 10px;
      background: #003dab;
      border-color: #0078ff;
      color: #91b2f4;
      font-size: 16px;
      margin-top: 0;
      margin-left: 0;
      -webkit-box-shadow: 0 0 0 1000px #003dab inset;
      &::placeholder {
        color: #91b2f4;
      }
    }
    /deep/ .el-form-item__label {
      // padding: 19px 12px 15px 0;
    }
    /deep/ .el-form-item {
      // margin-bottom: 0;
    }
    .bottom {
      text-align: center;
      margin-top: 50px;
      .el-button {
        width: 200px;
        height: 50px;
        color: #fff;
      }
    }
  }
}
</style>
