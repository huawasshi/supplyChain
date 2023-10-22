<!--
 * @Author: yanchenxi
 * @Date: 2022-05-06 14:21:53
 * @LastEditTime: 2023-10-21 10:25:43
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \world-demo\src\views\login\Login.vue
-->
<template>
  <div>
    <div class="header-box"></div>
    <div class="content">
      <div class="content-img"></div>
      <div class="login-box">
        <div class="form-box">
          <el-form>
            <div class="title">{{isLogin?'Welcome to login':'User Registration'}}</div>
            <el-form-item prop="username">
              <el-input
                class="input"
                placeholder="Please enter your account"
                v-model="form.name"
                :maxlength="16"
                type="text"
              >
                <template slot="prepend">
                  <div class="myicon"></div>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                class="input"
                placeholder="Please enter your password"
                v-model="form.password"
                :maxlength="16"
                type="password"
              >
                <template slot="prepend">
                  <div class="myiconP"></div>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password" v-if="!isLogin" class="myselect">
              <el-form-item>
                <el-select
                  v-model="form.registration_type"
                  placeholder="please choose your role"
                  style="width:100%"
                >
                  <el-option label="生产商" value="生产商"></el-option>
                  <el-option label="经销商" value="经销商"></el-option>
                  <el-option label="消费者" value="消费者"></el-option>
                </el-select>
              </el-form-item>
            </el-form-item>
            <el-form-item class="check" v-if="isLogin">
              <el-checkbox v-model="checked">
                <span style="color:#fff">remember password</span>
              </el-checkbox>
            </el-form-item>
            <el-form-item>
              <el-button
                class="button"
                type="primary"
                @click="loginOn"
              >{{isLogin?"logging":'registering'}}</el-button>
              <div class="goRe" v-if="isLogin">
                <span>
                  <span>No account？</span>
                  <span class="onRe" @click="isLogin=!isLogin">Going to register</span>
                </span>
              </div>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { registerOn } from '@apis/user.js';
export default {
  name: 'Login',
  data() {
    return {
      redirect: '',
      otherQuery: {},
      isLogin: true,
      checked: true,
      form: { name: '', password: '', registration_type: '' }
    };
  },
  components: {},
  watch: {
    $route: {
      handler(route) {
        console.log(route);
        const query = route.query;
        if (query) {
          this.redirect = query.redirect;
          this.otherQuery = this.getOtherQuery(query);
        }
      },
      immediate: true
    }
  },
  methods: {
    loginOn() {
      console.log(this.form);
      if (!this.isLogin) {
        // 注册
        registerOn(this.form)
          .then(() => {
            this.$message.success('login was successful');
            this.isLogin = true;
          })
          .catch(() => {});
        return;
      }
      // 登陆
      this.$store.dispatch('login', this.form).then(() => {
        // 重定向到首页或者token失效了退出界面，再次登陆就会回到原来所在的页面
        this.$router.push({
          path: this.redirect || '/',
          query: this.otherQuery
        });
      });
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur];
        }
        return acc;
      }, {});
    }
  }
};
</script>

<style lang="less" scoped>
.content {
  display: flex;
  align-items: center;
  justify-content: space-evenly;
  height: 100vh;
  background-image: url('../../assets/images/bg.png');
  background-size: 100% 100%;
  background-repeat: no-repeat;
  .content-img {
    background-image: url('../../assets/images/img.png');
    background-size: 100% 100%;
    background-repeat: no-repeat;
    height: 500px;
    width: 650px;
  }
  .login-box {
    .form-box {
      .el-form {
        background-image: url('../../assets/images/border.png');
        background-size: 100% 100%;
        background-repeat: no-repeat;
        width: 450px;
        min-height: 400px;
        text-align: center;
        padding: 0 40px;
      }
      .check {
        text-align: right;
      }
      .myicon {
        background-image: url('../../assets/images/tb1.png');
        background-size: 100% 100%;
        background-repeat: no-repeat;
        width: 20px;
        height: 24px;
      }
      .myiconP {
        background-image: url('../../assets/images/tb2.png');
        background-size: 100% 100%;
        background-repeat: no-repeat;
        width: 20px;
        height: 24px;
      }
      .title {
        text-align: center;
        color: #fff;
        font-size: 24px;
        width: 100%;
        padding: 30px 0;
      }
      /deep/ .el-input__inner {
        height: 50px;
        // border-radius: 10px;
        background: #003dab;
        border-color: #0078ff;
        border-left: none;
        color: #91b2f4;
        font-size: 16px;
        margin-top: 0;
        margin-left: 0;
        -webkit-box-shadow: 0 0 0 1000px #003dab inset;
        &::placeholder {
          color: #91b2f4;
        }
      }
      .myselect /deep/ .el-input__inner {
        border-left: 1px solid #0078ff;
      }
      /deep/ .el-checkbox__inner {
        background: #003dab;
        border-color: #fff;
      }
      /deep/ .el-input-group__prepend {
        background: #003dab;
        border-color: #0078ff;
        border-right: none;
      }
      /deep/ .el-input {
        margin: 0 !important;
      }
      .button {
        height: 50px;
        border-radius: 5px;
        width: 100%;
        font-size: 18px;
        margin-left: 5px;
        background: #0090ff;
      }
    }
  }
  .goRe {
    text-align: left;
    color: #fff;
  }
  .onRe {
    color: #51a1ff;
    cursor: pointer;
  }
}
.header-box {
  height: 7vh;
  position: absolute;
  width: 100%;
  background-image: url('../../assets/images/header.png');
  background-size: 100% 100%;
  background-repeat: no-repeat;
  left: 0;
  top: 2vh;
  z-index: 1000;
}
input:-webkit-autofill {
  color: #fff;
  -webkit-text-fill-color: #fff;
}
</style>
