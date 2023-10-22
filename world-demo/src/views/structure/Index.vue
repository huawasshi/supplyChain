<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 19:36:42
 * @LastEditTime: 2022-08-04 14:49:36
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\structure\Index.vue
-->
<template>
  <div>
    <div class="search">
      <bfe-form :inline="true">
        <div class="row">
          <div class="col col-md-4">
            <bfe-form-item label="任务名称">
              <bfe-input placeholder="请输入任务名称" v-model="queryData.name"></bfe-input>
            </bfe-form-item>
          </div>
          <div class="col col-md-4">
            <bfe-button type="primary" icon="search" @click="query">查 询</bfe-button>
            <bfe-button icon="ios-undo-outline" @click="init">重 置</bfe-button>
          </div>
          <div class="col col-md-4" style="text-align:right">
            <bfe-upload ref="upload2" action="http://22.11.38.153/posts/" style="float:right;"
              :auto-upload="false">
              <bfe-button type="primary" icon="upload">导 入</bfe-button>
            </bfe-upload>
            <bfe-button type="primary" icon="plus" style="float:right;margin-right:10px;"
              @click="addDetail('add')">新 增</bfe-button>
          </div>
        </div>
      </bfe-form>
    </div>
    <div class="tableContent">
      <bfe-table :data="tableData" style="width: 100%" border v-loading="tbloading">
        <!-- TODO 根据后端数据算编号 -->
        <!--bfe-table-column prop="date" label="编号" show-overflow-tooltip align="center"></bfe-table-column-->
        <bfe-table-column prop="a1" label="任务ID" show-overflow-tooltip align="center">
        </bfe-table-column>
        <bfe-table-column prop="a2" label="任务名" show-overflow-tooltip align="center">
        </bfe-table-column>
        <bfe-table-column prop="sdb" label="源数据源" show-overflow-tooltip align="center">
        </bfe-table-column>
        <bfe-table-column prop="ddb" label="目标数据源" show-overflow-tooltip align="center">
        </bfe-table-column>
        <!-- <bfe-table-column prop="a3" label="任务状态"
          show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <bfe-switch v-model="scope.row.a3"
              style="left: 20px;right:0px"
              on-color="#13ce66"
              off-color="#ff4949"></bfe-switch>
          </template>
        </bfe-table-column>-->
        <!-- <bfe-table-column prop="a4" label="创建时间"
          show-overflow-tooltip align="center">
        </bfe-table-column>-->
        <bfe-table-column prop="a5" label="修改时间" show-overflow-tooltip align="center">
        </bfe-table-column>
        <bfe-table-column prop="a6" label="最近执行时间" show-overflow-tooltip align="center">
        </bfe-table-column>
        <!-- bfe-table-column prop="a7" label="备注" show-overflow-tooltip align="center"></bfe-table-column-->
        <bfe-table-column label="操作" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <!-- TODO 根据返回数据判断是停用或者启用 -->
            <!-- bfe-button type="text" size="small">启用</bfe-button -->

            <!-- <bfe-button slot="trigger" size="small" type="text">导入配置</bfe-button> -->
            <!-- <bfe-button type="text" size="small"
                style="color:green"
                @click="onDialog('edit',scope.row)">
                <i
                  class="istyle bfe-icon-edit bfe-icon--right "></i>
            </bfe-button>-->

            <bfe-tooltip content="编辑" placement="top">
              <bfe-button type="text" size="small" @click="addDetail('edit',scope.row)">
                <i class="istyle bfe-icon-edit bfe-icon--right"></i>
              </bfe-button>
            </bfe-tooltip>
            <bfe-tooltip content="删除" placement="top">
              <bfe-button type="text" size="small" @click="handleDelete(scope.$index)">
                <i class="istyle bfe-icon-delete bfe-icon--right"></i>
              </bfe-button>
            </bfe-tooltip>
            <bfe-tooltip content="查看" placement="top">
              <bfe-button type="text" size="small" @click="goDetail(scope.row)">
                <i class="istyle bfe-icon-eye bfe-icon--right"></i>
              </bfe-button>
            </bfe-tooltip>
            <bfe-tooltip content="导出" placement="top">
              <bfe-button style="margin-left: 10px;" size="small" type="text"
                @click="tableToExcel()">
                <i class="istyle bfe-icon-ios-cloud-download bfe-icon--right"></i>
              </bfe-button>
            </bfe-tooltip>
            <bfe-tooltip content="启动" placement="top">
              <bfe-button style="margin-left: 10px;" size="small" type="text"
                @click="runDdlMig(scope.row)">
                <i class="istyle bfe-icon-play bfe-icon--right"></i>
              </bfe-button>
            </bfe-tooltip>

          </template>
        </bfe-table-column>
      </bfe-table>
      <div class="table-page">
        <!-- TODO 根据返回数据再编写跳转分页逻辑 -->
        <bfe-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
          :current-page="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="10"
          layout="total, sizes, prev, pager, next, jumper" :total="tableDataTemp.length">
        </bfe-pagination>
      </div>
    </div>
  </div>
</template>

<script>
// import JsonExcel from 'vue-json-excel'       //在全局main.js中导入引用
// Vue.component('downloadExcel', JsonExcel)
export default {
  name: 'Structure',

  data() {
    return {
      tbloading: false,
      dialogData: {
        type: 'add',
        row: null
      },
      dialogVisible: false,
      queryData: { name: '' },
      currentPage: 1,
      tableDataTemp: [
        {
          a1: '1',
          a2: '迁移任务-验证表1-10',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23:21',
          a7: '这是数据库'
        },
        {
          a1: '2',
          a2: '迁移任务-验证表11-20',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '3',
          a2: '13名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '4',
          a2: '11名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '5',
          a2: '11名称',
          a3: true,
          dtype: 'tdsql',
          stype: 'oracle',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '6',
          a2: '11名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '7',
          a2: '11名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '8',
          a2: '11名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        }
      ],
      tableData: [
        {
          a1: '1',
          a2: '11名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '2',
          a2: '12名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '3',
          a2: '13名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '4',
          a2: '11名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '5',
          a2: '11名称',
          a3: true,
          dtype: 'tdsql',
          stype: 'oracle',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '6',
          a2: '11名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '7',
          a2: '11名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        },
        {
          a1: '8',
          a2: '11名称',
          a3: '1',
          get sdb() {
            return '源数据库' + this.a1
          },
          get ddb() {
            return '目标数据库' + this.a1
          },
          a4: '2022-07-27',
          a5: '2022-07-27 16:19',
          a6: '2022-07-27 18:23'
        }
      ]
    }
  },
  mounted() {
    this.query()
  },
  methods: {
    //新增修改详情
    addDetail(type = 'add', row = {}) {
      this.$router.push({
        name: 'AddDataStructureDetail',
        query: { type, row }
      })
    },
    //查询
    query() {
      if (this.queryData.name === '') {
        this.tableData = this.tableDataTemp
      } else {
        this.tableData = []
        for (let i = 0; i < this.tableDataTemp.length; ++i) {
          if (this.tableDataTemp[i].a2 === this.queryData.name) {
            this.tableData.push(this.tableDataTemp[i])
          }
        }
      }
    },
    //重置
    init() {
      this.queryData.name = ''
    },
    del(index) {
      console.log(index)
      this.$confirm('确认要删除这行数据吗?', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.tableData.splice(index, 1)
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    //导出
    tableToExcel() {
      // 要导出的json数据
      var jsonData = [
        {
          name: '路人甲',
          phone: '123456789',
          email: '000@123456.com'
        },
        {
          name: '炮灰乙',
          phone: '123456789',
          email: '000@123456.com'
        },
        {
          name: '土匪丙',
          phone: '123456789',
          email: '000@123456.com'
        },
        {
          name: '流氓丁',
          phone: '123456789',
          email: '000@123456.com'
        }
      ]
      // 列标题，逗号隔开，每一个逗号就是隔开一个单元格
      let str = `姓名,电话,邮箱\n`
      // 增加\t为了不让表格显示科学计数法或者其他格式
      for (let i = 0; i < jsonData.length; i++) {
        for (const key in jsonData[i]) {
          str += `${jsonData[i][key] + '\t'},`
        }
        str += '\n'
      }
      // encodeURIComponent解决中文乱码
      let uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str)
      // 通过创建a标签实现
      var link = document.createElement('a')
      link.href = uri
      // 对下载的文件命名
      link.download = '任务配置表.csv'
      link.click()
      document.body.removeChild(link)
    },

    handleSizeChange(val) {
      console.log(val)
    },
    handleCurrentChange(val) {
      console.log(val)
    },
    onDialog(type, row = {}) {
      this.dialogVisible = true
      this.dialogData.type = type
      this.dialogData.row = type === 'add' ? null : row
    },

    // 删除+
    handleDelete: function(index) {
      // this.dialogVisible = true;
      // this.dialogData.type = type;
      // this.dialogData.row = type === 'del' ? null : row;
      // let data = {id:params.id}
      //   this.$confirm('确定要删除该条数据吗？', '提示', {}).then(() => {
      //     this.$api.dailyPlan.deleteById(data).then((res) => {
      //       if (res.code == 200) {
      //         this.$message({message: '删除成功', type: 'success'})
      //         this.findPage()
      //       } else {
      //         this.$message({ message: '删除失败, ' + res.msg, type: 'error'})
      //       }
      //     }).catch((res) => {
      //       this.$message({message: res.message,type: 'error'})
      //     })
      //   })

      console.log(index)
      this.$confirm('确认要删除这行数据吗?', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.tableData.splice(index, 1)
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    }, //+

    goDetail(row) {
      this.$router.push({ name: 'StructureDetail', query: row })
    },
    runDdlMig(row) {
      this.tbloading = true
      console.log('run ddl mig' + row.a2)
      const h = this.$createElement
      this.$notify({
        title: '表结构迁移运行',
        message: h('i', { style: 'color: teal' }, '表结构迁移运行：' + row.a2)
      })
      this.tbloading = false
    }
  }
}
</script>

<style lang="less" scoped>
.istyle {
  font-size: 16px;
}
</style>


