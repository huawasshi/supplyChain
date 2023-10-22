<!--
 * @Author: yanchenxi
 * @Date: 2022-06-13 20:00:15
 * @LastEditTime: 2022-08-05 13:54:29
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\dataTransfer\components\AddEdit.vue
-->
<template>
  <div>
    <bfe-form ref="form" :model="form" label-width="150px">
      <div class="row">
        <div class="col col-md-4">
          <bfe-form-item label="任务名称">
            <bfe-input v-model="form.a1"></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-4">
          <bfe-form-item label="任务类型">
            <bfe-select v-model="form.a2" placeholder="请选择">
              <bfe-option label="全量" value="1"></bfe-option>
              <bfe-option label="增量" value="2"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col col-md-4">
          <bfe-form-item label="迁移数据源类型">
            <bfe-select v-model="form.a8" placeholder="请选择">
              <bfe-option label="ORACLE" value="oracle"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
        <div class="col col-md-4">
          <bfe-form-item label="目标数据源类型">
            <bfe-select v-model="form.a9" placeholder="请选择">
              <bfe-option label="TDSQL" value="tdsql"></bfe-option>
              <bfe-option label="MYSQL" value="mysql"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col col-md-4">
          <bfe-form-item label="迁移数据源名称">
            <bfe-select v-model="form.a3" placeholder="请选择">
              <bfe-option label="源数据源1" value="11"></bfe-option>
              <bfe-option label="源数据源2" value="22"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
        <div class="col col-md-4">
          <bfe-form-item label="目标数据源名称">
            <bfe-select v-model="form.a4" placeholder="请选择">
              <bfe-option label="目标数据源1" value="11"></bfe-option>
              <bfe-option label="目标数据源2" value="22"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div>
    </bfe-form>
    <SelectTree :arr="arr" :key="i" :rightArr="rightArr" />
    <div class="row">
      <div class="col col-md-8" style="text-align:center;margin-top:30px">
        <bfe-button @click="cancels">取 消</bfe-button>
        <bfe-button type="primary" @click="confirms">确 定</bfe-button>
      </div>
    </div>
  </div>
</template>

<script>
import SelectTree from '@components/SelectTree.vue';
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
  components: { SelectTree },
  data() {
    return {
      isShow: this.dialogVisible,
      form: {
        a1: '全量迁移',
        a2: '1',
        a3: '11',
        a4: '11',
        a8: 'oracle',
        a9: 'tdsql'
      },
      arr: [],
      rightArr: [],
      i: 1
    };
  },
  watch: {
    'form.a3'() {
      this.arr = [
        {
          checked: false,
          label: 'table2',
          id: 222,
          children: [
            {
              id: 22,
              label: '迁移通道数：',
              value: '1'
            },
            {
              id: 23,
              label: '迁移前执行SQL：'
            },
            {
              id: 24,
              label: '原库取数SQL:',
              value: 'select * from table2'
            },
            {
              id: 25,
              label: '迁移后执行SQL:'
            },
            {
              id: 26,
              label: '增量条件：'
            },
            {
              id: 27,
              label: '系统数据迁移验证SQL：',
              value: 'select count(*1) from table2'
            },
            {
              id: 28,
              label: '自定义数据迁移验证SQL：'
            }
          ]
        },
        {
          checked: false,
          label: 'table3',
          id: 3333,
          children: [
            {
              id: 32,
              label: '迁移通道数：',
              value: '1'
            },
            {
              id: 33,
              label: '迁移前执行SQL：'
            },
            {
              id: 34,
              label: '原库取数SQL:',
              value: 'select * from table3'
            },
            {
              id: 35,
              label: '迁移后执行SQL:'
            },
            {
              id: 36,
              label: '增量条件：'
            },
            {
              id: 37,
              label: '系统数据迁移验证SQL：',
              value: 'select count(*1) from table3'
            },
            {
              id: 38,
              label: '自定义数据迁移验证SQL：'
            }
          ]
        },
        {
          checked: false,
          label: 'table4',
          id: 9999,
          children: [
            {
              id: 92,
              label: '迁移通道数：',
              value: '1'
            },
            {
              id: 93,
              label: '迁移前执行SQL：'
            },
            {
              id: 94,
              label: '原库取数SQL:',
              value: 'select * from table4'
            },
            {
              id: 95,
              label: '迁移后执行SQL:'
            },
            {
              id: 96,
              label: '增量条件：'
            },
            {
              id: 97,
              label: '系统数据迁移验证SQL：',
              value: 'select count(*1) from table4'
            },
            {
              id: 98,
              label: '自定义数据迁移验证SQL：'
            }
          ]
        }
      ];
      console.log('12321');
      this.rightArr = [
        {
          checked: false,
          label: 'table1',
          id: 1,
          children: [
            {
              id: 2,
              label: '迁移通道数：',
              value: '1'
            },
            {
              id: 3,
              label: '迁移前执行SQL：'
            },
            {
              id: 4,
              label: '原库取数SQL:',
              value: 'select * from table1'
            },
            {
              id: 5,
              label: '迁移后执行SQL:'
            },
            {
              id: 6,
              label: '增量条件：'
            },
            {
              id: 7,
              label: '系统数据迁移验证SQL：',
              value: 'select count(*1) from table1'
            },
            {
              id: 8,
              label: '自定义数据迁移验证SQL：'
            }
          ]
        }
      ];
      // 强制刷新dom
      this.i++;
    }
  },
  mounted() {
    console.log('test');
    // this.form = Object.assign(this.form, this.$route.query.data);
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
    },

    confirms() {
      this.$router.push({
        path: '/dataTransfer/index'
      });
    },
    cancels() {
      this.$router.push({
        path: '/dataTransfer/index'
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