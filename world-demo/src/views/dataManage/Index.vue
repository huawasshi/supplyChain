<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 19:23:32
 * @LastEditTime: 2022-08-16 09:53:30
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\views\dataManage\Index.vue
-->
<template>
  <div>
    <div class="search">
      <bfe-form :inline="true">
        <div class="row">
          <div class="col col-md-4">
            <bfe-form-item label="数据源名称">
              <bfe-input placeholder="请输入数据源名称" v-model="queryData.name"></bfe-input>
            </bfe-form-item>
          </div>
          <div class="col col-md-4">
            <bfe-button type="primary" icon="search" @click="query">查 询</bfe-button>
            <bfe-button icon="ios-undo-outline" @click="init">重 置</bfe-button>
          </div>
          <div class="col col-md-4" style="text-align:right">
            <bfe-button type="primary" icon="plus" @click="onDialog('add',{})">新 增</bfe-button>
          </div>
        </div>
      </bfe-form>
    </div>
    <div class="tableContent">
      <bfe-table :data="tableData" style="width: 100%" border>
        <!-- TODO 根据后端数据算编号 -->
        <bfe-table-column type="index" width="50"></bfe-table-column>
        <bfe-table-column prop="a1" label="数据源类型" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a2" label="数据源名称" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a3" label="IP地址" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a4" label="端口" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a5" label="数据库名/SID" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a6" label="用户名" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a7" label="链接状态" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column label="操作" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <bfe-tooltip content="编辑" placement="top">
              <bfe-button
                type="text"
                size="small"
                @click="onDialog('edit',scope.row)"
                class="bfe-icon-edit"
              ></bfe-button>
            </bfe-tooltip>
            <bfe-tooltip content="删除" placement="top">
              <bfe-button
                type="text"
                size="small"
                @click="del(scope.$index)"
                class="bfe-icon-delete"
              ></bfe-button>
            </bfe-tooltip>
            <bfe-tooltip content="详情" placement="top">
              <bfe-button
                type="text"
                size="small"
                @click="onDialog('detail',scope.row)"
                class="bfe-icon-eye"
              ></bfe-button>
            </bfe-tooltip>
            <!-- <bfe-tooltip content="启用" placement="top">
           
              <bfe-button type="text" size="small" class="bfe-icon-document"></bfe-button>
            </bfe-tooltip>-->
          </template>
        </bfe-table-column>
      </bfe-table>

      <div class="table-page">
        <!-- TODO 根据返回数据再编写跳转分页逻辑 -->
        <bfe-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="6"
        ></bfe-pagination>
      </div>

      <!-- 新增/修改 弹框 -->
      <div v-if="dialogVisible">
        <add-edit :dialogVisible.sync="dialogVisible" :dialogData="dialogData" />
      </div>
    </div>
  </div>
</template>

<script>
import AddEdit from './components/AddEdit';
export default {
  name: 'dataManage',
  components: { AddEdit },
  data() {
    return {
      dialogData: {
        type: 'add',
        row: {}
      },
      dialogVisible: false,
      queryData: { name: '' },
      currentPage: 1,
      tableDataTemp: [
        {
          a1: 'TDSQL',
          a2: '数据源1',
          a3: '1.1.1.1',
          a4: '10',
          a5: '数据库1',
          a6: 'use1',
          a7: '正常',
          a8: '12123'
        },
        {
          a1: 'ORACLE',
          a2: '数据源2',
          a3: '1.1.1.2',
          a4: '20',
          a5: '数据库2',
          a6: 'use2',
          a7: '正常',
          a8: '12123'
        },
        {
          a1: 'TDSQL',
          a2: '数据源3',
          a3: '1.1.1.3',
          a4: '30',
          a5: '数据库3',
          a6: 'use3',
          a7: '正常',
          a8: '12123'
        },
        {
          a1: 'ORACLE',
          a2: '数据源4',
          a3: '1.1.1.4',
          a4: '40',
          a5: '数据库4',
          a6: 'use4',
          a7: '正常',
          a8: '12123'
        },
        {
          a1: 'ORACLE',
          a2: '数据源5',
          a3: '1.1.1.5',
          a4: '50',
          a5: '数据库5',
          a6: 'use5',
          a7: '正常',
          a8: '12123'
        }
      ],
      tableData: [
        {
          a1: 'TDSQL',
          a2: '数据源1',
          a3: '1.1.1.1',
          a4: '10',
          a5: '数据库1',
          a6: 'use1',
          a7: '正常',
          a8: '12123'
        },
        {
          a1: 'ORACLE',
          a2: '数据源2',
          a3: '1.1.1.2',
          a4: '20',
          a5: '数据库2',
          a6: 'use2',
          a7: '正常',
          a8: '12123'
        },
        {
          a1: 'TDSQL',
          a2: '数据源3',
          a3: '1.1.1.3',
          a4: '30',
          a5: '数据库3',
          a6: 'use3',
          a7: '正常',
          a8: '12123'
        },
        {
          a1: 'ORACLE',
          a2: '数据源4',
          a3: '1.1.1.4',
          a4: '40',
          a5: '数据库4',
          a6: 'use4',
          a7: '正常',
          a8: '12123'
        },
        {
          a1: 'ORACLE',
          a2: '数据源5',
          a3: '1.1.1.5',
          a4: '50',
          a5: '数据库5',
          a6: 'use5',
          a7: '正常',
          a8: '12123'
        }
      ]
    };
  },
  methods: {
    handleSizeChange(val) {
      console.log(val);
    },
    handleCurrentChange(val) {
      console.log(val);
    },
    onDialog(type, row) {
      this.dialogVisible = true;
      this.dialogData.type = type;
      this.dialogData.row = type === 'add' ? {} : row;
    },
    query() {
      if (this.queryData.name === '') {
        this.tableData = this.tableDataTemp;
      } else {
        this.tableData = [];
        for (let i = 0; i < this.tableDataTemp.length; ++i) {
          if (this.tableDataTemp[i].a2 === this.queryData.name) {
            this.tableData.push(this.tableDataTemp[i]);
          }
        }
      }
    },
    init() {
      this.queryData.name = '';
    },
    del(index) {
      console.log(index);
      this.$confirm('确认要删除这行数据吗?', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.tableData.splice(index, 1);
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
    },

    goDetail() {
      this.$router.push({ name: 'TimeingDetail' });
    }
  }
};
</script>

<style>
</style>