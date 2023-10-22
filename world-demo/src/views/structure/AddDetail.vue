<!--
 * @Author: yanchenxi
 * @Date: 2022-06-13 20:00:15
 * @LastEditTime: 2022-08-04 15:00:22
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\structure\AddDetail.vue
-->
<template>
  <div>
    <bfe-form ref="form" :model="form" label-width="150px">
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="任务名称">
            <bfe-input v-model="form.a2"></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <!-- <bfe-form-item label="任务类型">
            <bfe-select v-model="form.a2" placeholder="请选择">
              <bfe-option label="全量" value="shanghai"></bfe-option>
              <bfe-option label="增量" value="beijing"></bfe-option>
            </bfe-select>
          </bfe-form-item> -->
        </div>
      </div>
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="迁移数据源类型">
            <bfe-select v-model="form.stype" placeholder="请选择">
              <bfe-option label="ORACLE" value="oracle"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="目标数据源类型">
            <bfe-select v-model="form.dtype" placeholder="请选择">
              <bfe-option label="TDSQL" value="tdsql"></bfe-option>
              <bfe-option label="MYSQL" value="mysql"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="迁移数据源名称">
            <!-- <bfe-input v-model="form.a3"></bfe-input> -->
            <bfe-select v-model="form.sdb" placeholder="请选择">
              <bfe-option label="迁移数据源1" value="11"></bfe-option>
              <bfe-option label="迁移数据源2" value="22"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="目标数据源名称">
            <!-- <bfe-input v-model="form.a4"></bfe-input> -->
            <bfe-select v-model="form.ddb" placeholder="请选择">
              <bfe-option label="目标数据源1" value="11"></bfe-option>
              <bfe-option label="目标数据源2" value="22"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div>
    </bfe-form>
    <SelectTree :arr="arr" :key="i" />
    <div style="text-align:center;margin-top:30px">
      <bfe-button @click="cancels">取 消</bfe-button>
      <bfe-button type="primary" @click="confirms">确 定</bfe-button>
    </div>
  </div>
</template>

<script>
import SelectTree from '@components/SelectTree.vue'
export default {
  components: { SelectTree },
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
        stype: '',
        dtype: '',
        sdb: '',
        ddb: '',
        a4: '',
        a5: '',
        a6: ''
      },
      arr: [],
      i: 1
    }
  },
  watch: {
    'form.sdb'() {
      this.arr = [
        {
          checked: false,
          label: 'table1',
          id: 1,
          children: [
            {
              id: 12,
              label: '源表结构：'
            },
            {
              id: 13,
              label: '目标表结构：'
            },
            {
              id: 14,
              label: '源表索引:',
              value: 'select * from table1'
            },
            {
              id: 15,
              label: '目标表索引:'
            }
          ]
        },
        {
          checked: false,
          label: 'table2',
          id: 2222,
          children: [
            {
              id: 22,
              label: '源表结构：'
            },
            {
              id: 23,
              label: '目标表结构：'
            },
            {
              id: 24,
              label: '源表索引:',
              value: 'select * from table1'
            },
            {
              id: 25,
              label: '目标表索引:'
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
              label: '源表结构：'
            },
            {
              id: 33,
              label: '目标表结构：'
            },
            {
              id: 34,
              label: '源表索引:',
              value: 'select * from table1'
            },
            {
              id: 35,
              label: '目标表索引:'
            }
          ]
        },
        {
          checked: false,
          label: 'table4',
          id: 9999,
          children: [
            {
              id: 42,
              label: '源表结构：'
            },
            {
              id: 43,
              label: '目标表结构：'
            },
            {
              id: 44,
              label: '源表索引:',
              value: 'select * from table1'
            },
            {
              id: 45,
              label: '目标表索引:'
            }
          ]
        }
      ]
      console.log('12321')
      // 强制刷新dom
      this.i++
    }
  },
  mounted() {
    const { type, row } = this.$route.query
    if (type === 'edit') {
      this.$route.meta.title = '结构迁移编辑'
      Object.keys(this.form).map(v => {
        this.form[v] = row[v]
      })
    } else {
      this.$route.meta.title = '结构迁移新增'
    }
    //this.form=this.$route.query.data
  },
  methods: {
    channel() {
      this.isShow = false
      this.$emit('update:dialogVisible', false)
    },
    show() {
      this.isShow = true
      console.log(this.dialogData)
      this.$emit('update:dialogVisible', false)
    },
    onSubmit() {
      console.log('submit!')
    },

    confirms() {
      this.$router.push({
        path: '/structure/index'
      })
    },
    cancels() {
      this.$router.push({
        path: '/structure/index'
      })
    }
  }
}
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