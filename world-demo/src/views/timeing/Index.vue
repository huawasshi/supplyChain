<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 19:36:42
 * @LastEditTime: 2022-08-10 10:27:46
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\timeing\Index.vue
-->
<template>
  <div>
    <div class="search">
      <bfe-form :inline="true">
        <div class="row">
          <div class="col col-md-4 test">
            <bfe-form-item label="任务名称">
              <bfe-input placeholder="请输入任务名称" v-model="queryData.name"></bfe-input>
            </bfe-form-item>
          </div>
          <div class="col col-md-4">
            <bfe-button type="primary" icon="search" @click="query">查 询</bfe-button>
            <bfe-button icon="ios-undo-outline" @click="init">重 置</bfe-button>
          </div>
          <div class="col col-md-4" style="text-align:right;">
            <bfe-upload
              ref="upload2"
              action="http://22.11.38.153/posts/"
              :auto-upload="false"
              style="float:right;"
            >
              <bfe-button type="primary" icon="upload">导 入</bfe-button>
            </bfe-upload>
            <bfe-button
              type="primary"
              icon="plus"
              @click="onDialog('add')"
              style="float:right;margin-right:10px;"
            >新 增</bfe-button>
          </div>
        </div>
      </bfe-form>
    </div>
    <div class="tableContent">
      <bfe-table :data="tableData" style="width: 100%" border>
        <!-- TODO 根据后端数据算编号 -->
        <!-- <bfe-table-column prop="date" label="编号" show-overflow-tooltip align="center"></bfe-table-column> -->
        <bfe-table-column prop="a1" label="任务名称" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a2" label="迁移任务名称" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a3" label="定时执行条件" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a4" label="任务状态" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <bfe-switch
              v-model="scope.row.a4"
              on-color="#397ef6"
              @click.native.prevent="changeType(scope.row)"
            ></bfe-switch>
          </template>
        </bfe-table-column>
        <bfe-table-column prop="a5" label="创始人" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a6" label="创建时间" show-overflow-tooltip align="center"></bfe-table-column>
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
                @click="goDetail(scope.row)"
                class="bfe-icon-eye"
              ></bfe-button>
            </bfe-tooltip>
            <bfe-tooltip content="导出" placement="top">
              <bfe-button type="text" size="small" @click="tableToExcel()" class="bfe-icon-archive"></bfe-button>
            </bfe-tooltip>
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
          :total="8"
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
  name: 'Timeing',
  components: { AddEdit },
  data() {
    return {
      dialogData: {
        type: 'add',
        row: null
      },
      dialogVisible: false,
      queryData: { name: '' },
      currentPage: 1,
      tableData: [
        {
          a1: '测试任务名称',
          a2: '数据迁移任务1',
          a3: '10 1 * * * ',
          a4: true,
          a5: '王小虎',
          a6: '2016-05-02 09:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务2',
          a3: '20 1 * * * ',
          a4: false,
          a5: '王小虎',
          a6: '2016-05-02 09:30:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务3',
          a3: '30 1 * * * ',
          a4: true,
          a5: '王小虎',
          a6: '2016-05-02 09:40:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务4',
          a3: '40 1 * * * ',
          a4: false,
          a5: '王小虎',
          a6: '2016-05-02 10:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务5',
          a3: '50 1 * * * ',
          a4: true,
          a5: '王小虎',
          a6: '2016-05-02 11:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务6',
          a3: '60 1 * * * ',
          a4: false,
          a5: '王小虎',
          a6: '2016-05-02 12:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务7',
          a3: '70 1 * * * ',
          a4: true,
          a5: '王小虎',
          a6: '2016-05-02 13:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务8',
          a3: '80 1 * * * ',
          a4: false,
          a5: '王小虎',
          a6: '2016-05-02 13:20:10'
        }
      ],
      tableDataTemp: [
        {
          a1: '测试任务名称',
          a2: '数据迁移任务1',
          a3: '10 1 * * * ',
          a4: true,
          a5: '王小虎',
          a6: '2016-05-02 09:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务2',
          a3: '20 1 * * * ',
          a4: false,
          a5: '王小虎',
          a6: '2016-05-02 09:30:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务3',
          a3: '30 1 * * * ',
          a4: true,
          a5: '王小虎',
          a6: '2016-05-02 09:40:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务4',
          a3: '40 1 * * * ',
          a4: false,
          a5: '王小虎',
          a6: '2016-05-02 10:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务5',
          a3: '50 1 * * * ',
          a4: true,
          a5: '王小虎',
          a6: '2016-05-02 11:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务6',
          a3: '00 2 * * * ',
          a4: false,
          a5: '王小虎',
          a6: '2016-05-02 12:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务7',
          a3: '10 2 * * * ',
          a4: true,
          a5: '王小虎',
          a6: '2016-05-02 13:20:10'
        },
        {
          a1: '测试任务名称',
          a2: '数据迁移任务8',
          a3: '20 2 * * * ',
          a4: false,
          a5: '王小虎',
          a6: '2016-05-02 13:20:10'
        }
      ]
    };
  },
  methods: {
    changeType(row) {
      console.log(row);
      this.$confirm('是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          row.a4 = !row.a4;
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          });
        });
    },
    handleSizeChange(val) {
      console.log(val);
    },
    handleCurrentChange(val) {
      console.log(val);
    },
    onDialog(type, row = {}) {
      this.dialogVisible = true;
      this.dialogData.type = type;
      this.dialogData.row = type === 'add' ? null : row;
    },
    goDetail(row) {
      console.log(row);
      this.$router.push({
        path: '/timeing/detail',
        query: {
          data: row
        }
      });
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
      ];
      // 列标题，逗号隔开，每一个逗号就是隔开一个单元格
      let str = `姓名,电话,邮箱\n`;
      // 增加\t为了不让表格显示科学计数法或者其他格式
      for (let i = 0; i < jsonData.length; i++) {
        for (const key in jsonData[i]) {
          str += `${jsonData[i][key] + '\t'},`;
        }
        str += '\n';
      }
      // encodeURIComponent解决中文乱码
      let uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str);
      // 通过创建a标签实现
      var link = document.createElement('a');
      link.href = uri;
      // 对下载的文件命名
      link.download = '定时任务表.csv';
      link.click();
      document.body.removeChild(link);
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
    }
  }
};
</script>

<style lang="less" scoped>
</style>