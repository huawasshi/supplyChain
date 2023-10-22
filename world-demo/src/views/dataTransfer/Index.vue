<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 19:31:29
 * @LastEditTime: 2022-08-10 09:13:54
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\dataTransfer\Index.vue
-->
<template>
  <div>
    <div class="search">
      <bfe-form :inline="true">
        <div class="row">
          <div class="col col-md-4 test">
            <bfe-form-item label="任务名称">
              <bfe-input placeholder="请输入所属任务名称" v-model="queryData.name"></bfe-input>
            </bfe-form-item>
          </div>
          <div class="col col-md-4">
            <bfe-button type="primary" icon="search">查 询</bfe-button>
            <bfe-button icon="ios-undo-outline">重 置</bfe-button>
          </div>
          <div class="col col-md-4" style="text-align:right">
            <bfe-button
              type="primary"
              icon="plus"
              @click="addDetail('add')"
              style="margin-right:10px"
            >新 增</bfe-button>
            <bfe-upload
              ref="upload2"
              action="http://22.11.38.153/posts/"
              :auto-upload="false"
              style="float:right;"
            >
              <bfe-button type="primary" icon="upload">导 入</bfe-button>
            </bfe-upload>
          </div>
        </div>
      </bfe-form>
    </div>
    <div class="tableContent">
      <bfe-table :data="tableData" style="width: 100%" border>
        <!-- TODO 根据后端数据算编号 -->
        <!-- <bfe-table-column prop="date" label="编号" show-overflow-tooltip align="center"></bfe-table-column> -->
        <bfe-table-column type="index" label="序号" header-align="center" align="center" width="80"></bfe-table-column>
        <bfe-table-column prop="a1" label="任务名称" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a2" label="数据源名称" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a3" label="目标数据源名称" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a4" label="最近执行时间" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a5" label="执行状态" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a6" label="创建人" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column prop="a7" label="创建时间" show-overflow-tooltip align="center"></bfe-table-column>
        <bfe-table-column label="操作" show-overflow-tooltip align="center">
          <template slot-scope="scope">
            <bfe-tooltip content="编辑" placement="top">
              <bfe-button type="text" size="small" @click="edit(scope.row)" class="bfe-icon-edit"></bfe-button>
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
            <bfe-tooltip content="执行" placement="top">
              <bfe-button type="text" size="small" @click="hander" class="bfe-icon-clipboard"></bfe-button>
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
          :page-size="100"
          layout="total, sizes, prev, pager, next, jumper"
          :total="10"
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
  name: 'dateTransfer',
  components: { AddEdit },
  data() {
    return {
      dialogData: {
        type: 'add',
        row: null
      },
      detailDate: {
        row: Object
      },
      dialogVisible: false,
      queryData: { name: '' },
      currentPage: 1,
      tableData: [
        {
          a1: '数据迁移任务1',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        },
        {
          a1: '数据迁移任务2',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        },
        {
          a1: '数据迁移任务3',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        },
        {
          a1: '数据迁移任务4',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        },
        {
          a1: '数据迁移任务5',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        },
        {
          a1: '数据迁移任务6',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        },
        {
          a1: '数据迁移任务7',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        },
        {
          a1: '数据迁移任务8',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        },
        {
          a1: '数据迁移任务9',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        },
        {
          a1: '数据迁移任务10',
          a2: '测试数据源',
          a3: '目标数据源',
          a4: '2022-06-06 09:30:23',
          a5: '成功',
          a6: 'user',
          a7: '2016-05-02 10:11:23'
        }
      ]
    };
  },
  methods: {
    hander() {
      this.$message('任务提交成功，执行情况可前往详情查看');
    },
    handleSizeChange(val) {
      console.log(val);
    },
    handleCurrentChange(val) {
      console.log(val);
    },
    goDetail(row) {
      this.$router.push({
        path: '/dataTransfer/detail',
        query: {
          data: row
        }
      });
    },
    addDetail() {
      this.$router.push({ name: 'AddDataTransferDetail' });
    },
    edit(row) {
      this.$router.push({
        path: '/dataTransfer/addEdit',
        query: {
          data: row
        }
      });
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
      link.download = '数据迁移表.csv';
      link.click();
      document.body.removeChild(link);
    }
  }
};
</script>

<style>
</style>