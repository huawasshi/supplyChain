<!--
 * @Author       : zqp8340 zqp8340@bank-of-china.com
 * @Date         : 2022-07-26 08:54:37
 * @FilePath     : \frontend\src\views\structure\Detail.vue
 * @Desc         : 此文件
 * @LastEditors  : zqp8340 zqp8340@bank-of-china.com
 * @LastEditTime : 2022-08-09 15:28:17
-->

<template>
  <div>
    <bfe-form ref="form" :model="form" label-width="100px">
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="任务ID">
            <bfe-input v-model="form.a1" readonly></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="任务名称">
            <bfe-input v-model="form.a2" readonly></bfe-input>
          </bfe-form-item>
        </div>
      </div>
      <!-- <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="任务状态">
            <bfe-select v-model="form.a4" placeholder="请选择" :disabled="true">
              <bfe-option label="0" value="0">
              </bfe-option>
              <bfe-option label="1" value="1">
              </bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div> -->

      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="源数据源">
            <bfe-input v-model="form.sdb" readonly></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="目标数据源">
            <bfe-input v-model="form.ddb" readonly></bfe-input>
          </bfe-form-item>
        </div>
      </div>

      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="修改时间">
            <bfe-input v-model="form.a6" readonly></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="备注">
            <bfe-input v-model="form.a7" readonly></bfe-input>
          </bfe-form-item>
        </div>
      </div>
    </bfe-form>
    <div>
      <div class="tip">结构迁移表清单</div>
      <bfe-table ref='expandmigtable' :data="migtableData" style="width: 100%"
        @row-click="handrowclick">
        <bfe-table-column prop="tbn" label="表名" width="180px" show-overflow-tooltip align="center">
        </bfe-table-column>
        <!-- <bfe-table-column prop="ismig"
          label="是否迁移" show-overflow-tooltip
          align="center">
          <template slot-scope="scope">
            <bfe-switch v-model="scope.row.ismig"
              disabled on-color="#13ce66"
              off-color="#ff4949"></bfe-switch>
          </template>
        </bfe-table-column> -->
        <bfe-table-column prop="modtime" label="修改时间" show-overflow-tooltip align="center">
        </bfe-table-column>
        <bfe-table-column prop="remark" label="备注" show-overflow-tooltip align="center">
        </bfe-table-column>
        <!--template>
            <bfe-button type="text" size="small">详情</bfe-button>
          </template-->

        <bfe-table-column type="expand">
          <template slot-scope="props">
            <bfe-form label-position="left" inline class="demo-table-expand">
              <div class="strcss">
                <bfe-form-item label="源表结构 : " class="itemcss">
                  <bfe-input type="textarea" placeholder="请输入内容" autosize readonly class="inputcss"
                    v-model="props.row.sstruct">
                  </bfe-input>
                </bfe-form-item> <br />
                <bfe-form-item label="目标表结构 :  " class="itemcss">
                  <bfe-input type="textarea" placeholder="请输入内容" autosize readonly class="inputcss"
                    v-model="props.row.dstruct">
                  </bfe-input>
                </bfe-form-item><br />
              </div>
              <div class="strcss">
                <bfe-form-item label="源表索引 : " class="itemcss">
                  <bfe-input type="textarea" placeholder="请输入内容" autosize readonly class="inputcss"
                    v-model="props.row.sindex">
                  </bfe-input>
                </bfe-form-item><br />
                <bfe-form-item label="目标表索引 :  " class="itemcss">
                  <bfe-input type="textarea" autosize readonly placeholder="请输入内容" class="inputcss"
                    v-model="props.row.dindex">
                  </bfe-input>
                </bfe-form-item><br />
              </div>
            </bfe-form>
          </template>
        </bfe-table-column>

      </bfe-table>
    </div>
    <div>
      <div class="tip">任务执行记录</div>
      <bfe-table :data="tableData" ref="expandjobrecodetable" style="width: 100%"
        @row-click="handjobrecordrowclick">
        <bfe-table-column prop="taskRunid" label="任务执行ID" width="120px">
        </bfe-table-column>
        <!-- <bfe-table-column prop="taskName"
          label="任务名称" width="100px">
        </bfe-table-column> -->
        <bfe-table-column prop="runTime" label="执行时间"></bfe-table-column>
        <bfe-table-column prop="retMsg" label="执行结果"> <template slot-scope="scope">
            <div class="retcss">

              <div class="suc">
                <bfe-tooltip placement="top">
                  <div slot="content">
                    成功：{{scope.row.suc}}</div>
                  <span>{{scope.row.suc}}</span>
                </bfe-tooltip>

              </div>
              <div class="fail">
                <bfe-tooltip placement="top">
                  <div slot="content">
                    失败：{{scope.row.fail}}</div>
                  <span>{{scope.row.fail}}</span>
                </bfe-tooltip>

              </div>
              <div class="doing">
                <bfe-tooltip placement="top">
                  <div slot="content">
                    执行中：{{scope.row.doing}}
                  </div>
                  <span>{{scope.row.doing}}</span>
                </bfe-tooltip>

              </div>
            </div>
          </template></bfe-table-column>
        <bfe-table-column prop="retMsg" label="操作详情"> </bfe-table-column>
        <!--template>
            <bfe-button type="text" size="small">详情</bfe-button>
          </template-->

        <bfe-table-column type="expand">
          <template slot-scope="props">

            <bfe-form label-position="left" inline class="demo-table-expand">
              <!-- <div class="strcss">
                <bfe-form-item label="任务ID : " class="itemcss">
                  <span>{{ props.row.taskId }}</span>
                </bfe-form-item> <br />
                <bfe-form-item label="任务名 :  " class="itemcss">
                  <span>{{ props.row.taskName }}</span>
                </bfe-form-item><br />
              </div>
              <div class="strcss">
                <bfe-form-item label="任务执行ID : " class="itemcss">
                  <span>{{ props.row.taskRunid }}</span>
                </bfe-form-item><br />
                <bfe-form-item label="执行结果 :  " class="itemcss">
                  <span>{{ props.row.retMsg }}</span>
                </bfe-form-item><br />
              </div> -->
              <div class="strcss">
                <!-- <bfe-form-item label="执行状态 :  "
                  class="itemcss">
                  <span>{{ props.row.runStat }}</span>
                </bfe-form-item><br /> -->
                <bfe-form-item label="任务执行时间 :  " class="itemcss">
                  <span>{{ props.row.runTime }}</span>
                </bfe-form-item><br />
                <bfe-form-item label="完成时间 :  " class="itemcss">
                  <span>{{ props.row.finishTime }}</span>
                </bfe-form-item>
              </div>
              <div class="strcss">
                <bfe-table :data="props.row.rundata" style="width: 90%" :row-style="cellStyle">
                  <bfe-table-column prop="tablename" label="表名">
                  </bfe-table-column>
                  <bfe-table-column prop="status" label="执行状态">
                  </bfe-table-column>
                  <bfe-table-column prop="msg" label="执行结果">
                  </bfe-table-column>
                </bfe-table>
              </div>
            </bfe-form>
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
        a3: '11名称',
        a4: '1',
        a5: '4.221.44',
        sdb: '',
        ddb: '',
        a6: '133.2.41.44',
        a7: '无',
        a8: new Date()
      },
      migtableData: [
        {
          tbn: 'tbl_ddl_info',
          ismig: true,
          modtime: '2022-07-27',
          remark: '迁移结构表',
          sindex:
            'ALTER TABLE `tbl_ddl_info` ADD INDEX `index_1` USING BTREE (`tbl_name`, `is_migrate`, `src_db_indexes`);',
          dindex:
            'ALTER TABLE `tbl_ddl_info` ADD INDEX `index_1` USING BTREE (`tbl_name`, `is_migrate`, `src_db_indexes`);',
          sstruct:
            "CREATE TABLE `tbl_ddl_info` (`task_id` int(10) unsigned NOT NULL COMMENT '任务ID',`tbl_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',`is_migrate` char(1) COLLATE utf8_bin NOT NULL COMMENT '是否迁移',`src_db_indexes` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '源库索引',`des_db_indexes` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '目标库索引',`src_db_struct` text COLLATE utf8_bin NOT NULL COMMENT '源库表结构',`des_db_struct` text COLLATE utf8_bin NOT NULL COMMENT '目标库表结构',`mod_time` datetime DEFAULT NULL COMMENT '修改时间',`remark` varchar(999) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',PRIMARY KEY (`task_id`,`tbl_name`),KEY `idx_task_id` (`task_id`) USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='迁移任务表结构信息表';",
          dstruct:
            "CREATE TABLE `tbl_ddl_info` (`task_id` int(10) unsigned NOT NULL COMMENT '任务ID',`tbl_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',`is_migrate` char(1) COLLATE utf8_bin NOT NULL COMMENT '是否迁移',`src_db_indexes` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '源库索引',`des_db_indexes` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '目标库索引',`src_db_struct` text COLLATE utf8_bin NOT NULL COMMENT '源库表结构',`des_db_struct` text COLLATE utf8_bin NOT NULL COMMENT '目标库表结构',`mod_time` datetime DEFAULT NULL COMMENT '修改时间',`remark` varchar(999) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',PRIMARY KEY (`task_id`,`tbl_name`),KEY `idx_task_id` (`task_id`) USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='迁移任务表结构信息表';"
        },
        {
          tbn: 'tbl_ddl_detail',
          ismig: true,
          modtime: '2022-07-27',
          remark: '表结构迁移明细表',
          sindex:
            'ALTER TABLE `tbl_ddl_detail` ADD INDEX `index_1` USING BTREE (`is_migrate`, `src_db_indexes`);',
          dindex:
            'ALTER TABLE `tbl_ddl_detail` ADD INDEX `index_1` USING BTREE (`is_migrate`, `src_db_indexes`);',
          sstruct:
            "CREATE TABLE `tbl_ddl_detail` (`task_id` int(10) unsigned NOT NULL COMMENT '任务ID',`is_migrate` char(1) COLLATE utf8_bin NOT NULL COMMENT '是否迁移',`src_db_indexes` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '源库索引',`des_db_indexes` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '目标库索引',`src_db_struct` text COLLATE utf8_bin NOT NULL COMMENT '源库表结构',`des_db_struct` text COLLATE utf8_bin NOT NULL COMMENT '目标库表结构',`mod_time` datetime DEFAULT NULL COMMENT '修改时间',`remark` varchar(999) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',PRIMARY KEY (`task_id`,`tbl_name`),KEY `idx_task_id` (`task_id`) USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='迁移任务表结构信息表';",
          dstruct:
            "CREATE TABLE `tbl_ddl_detail` (`task_id` int(10) unsigned NOT NULL COMMENT '任务ID',`is_migrate` char(1) COLLATE utf8_bin NOT NULL COMMENT '是否迁移',`src_db_indexes` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '源库索引',`des_db_indexes` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '目标库索引',`src_db_struct` text COLLATE utf8_bin NOT NULL COMMENT '源库表结构',`des_db_struct` text COLLATE utf8_bin NOT NULL COMMENT '目标库表结构',`mod_time` datetime DEFAULT NULL COMMENT '修改时间',`remark` varchar(999) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',PRIMARY KEY (`task_id`,`tbl_name`),KEY `idx_task_id` (`task_id`) USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='迁移任务表结构信息表';"
        },
        {
          tbn: 'tbl_ddl_record',
          ismig: true,
          modtime: '2022-07-27',
          remark: '表结构迁移任务记录表',
          sindex: 'ALTER TABLE `tbl_ddl_record` ADD INDEX `index_1` USING BTREE (`tbl_name`);',
          dindex: 'ALTER TABLE `tbl_ddl_record` ADD INDEX `index_1` USING BTREE (`tbl_name`);',
          sstruct:
            "CREATE TABLE `tbl_ddl_record` (  `task_runid` int(10) unsigned NOT NULL COMMENT '任务执行ID',  `task_id` int(10) unsigned NOT NULL COMMENT '任务ID',  `tbl_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',  `run_stat` char(1) COLLATE utf8_bin NOT NULL COMMENT '0-待执行\n1-执行中\n2-执行成功\n3-执行失败',  `run_result` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '执行结果',  `run_time` datetime DEFAULT NULL COMMENT '任务执行时间',  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',  PRIMARY KEY (`task_runid`,`tbl_name`),  KEY `idx_task_runid` (`task_runid`) USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='表结构迁移任务记录表';",
          dstruct:
            "CREATE TABLE `tbl_ddl_record` (  `task_runid` int(10) unsigned NOT NULL COMMENT '任务执行ID',  `task_id` int(10) unsigned NOT NULL COMMENT '任务ID',  `tbl_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '表名',  `run_stat` char(1) COLLATE utf8_bin NOT NULL COMMENT '0-待执行\n1-执行中\n2-执行成功\n3-执行失败',  `run_result` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '执行结果',  `run_time` datetime DEFAULT NULL COMMENT '任务执行时间',  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',  PRIMARY KEY (`task_runid`,`tbl_name`),  KEY `idx_task_runid` (`task_runid`) USING BTREE) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='表结构迁移任务记录表';"
        },
        {
          tbn: 'tbl_ddl_rule',
          ismig: true,
          modtime: '2022-07-27',
          remark: '表结构迁移规则表',
          sindex:
            'ALTER TABLE `tbl_ddl_record` ADD INDEX `index_1` USING BTREE (`src_db_type`, `des_db_type`);',
          dindex:
            'ALTER TABLE `tbl_ddl_record` ADD INDEX `index_1` USING BTREE (`src_db_type`, `des_db_type`);',
          sstruct:
            "CREATE TABLE `tbl_ddl_rule` (  `rule_id` int(10) unsigned NOT NULL COMMENT '规则ID',  `src_db_type` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '源库类型',  `des_db_type` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '目标库类型',  `src_col_type` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '源库字段类型',  `des_col_type` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '目标库字段类型',  `cur_stat` char(1) COLLATE utf8_bin NOT NULL COMMENT '1-启用\n0-不启用',  `mod_time` datetime DEFAULT NULL COMMENT '修改时间',  `last_exec_time` datetime DEFAULT NULL COMMENT '最近执行时间',  `remark` varchar(999) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',  PRIMARY KEY (`rule_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='表结构迁移规则表';",
          dstruct:
            "CREATE TABLE `tbl_ddl_rule` (  `rule_id` int(10) unsigned NOT NULL COMMENT '规则ID',  `src_db_type` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '源库类型',  `des_db_type` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '目标库类型',  `src_col_type` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '源库字段类型',  `des_col_type` varchar(60) COLLATE utf8_bin NOT NULL COMMENT '目标库字段类型',  `cur_stat` char(1) COLLATE utf8_bin NOT NULL COMMENT '1-启用\n0-不启用',  `mod_time` datetime DEFAULT NULL COMMENT '修改时间',  `last_exec_time` datetime DEFAULT NULL COMMENT '最近执行时间',  `remark` varchar(999) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',  PRIMARY KEY (`rule_id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='表结构迁移规则表';"
        }
      ],
      tableData: [
        {
          taskRunid: '2',
          suc: 1,
          fail: 1,
          doing: 2,
          retMsg: 'tbl_ddl_detail 失败',
          runStat: '3',
          runTime: '2022-07-27 18:27:26',
          finishTime: '2022-07-27 18:27:27',
          rundata: [
            {
              tablename: 'tbl_ddl_info',
              status: '成功',
              msg: '成功'
            },
            {
              tablename: 'tbl_ddl_detail',
              status: '失败',
              msg: '字段无法创建'
            },
            {
              tablename: 'tbl_ddl_record',
              status: '执行中',
              msg: '待执行'
            },
            {
              tablename: 'tbl_ddl_rule',
              status: '执行中',
              msg: '待执行'
            }
          ]
        },
        {
          taskRunid: '1',
          suc: 4,
          fail: 0,
          doing: 0,
          retMsg: '完成',
          runStat: '1',
          runTime: '2022-07-27 13:12:30',
          finishTime: '2022-07-27  13:12:35',
          rundata: [
            {
              tablename: 'tbl_ddl_info',
              status: '成功',
              msg: '成功'
            },
            {
              tablename: 'tbl_ddl_detail',
              status: '成功',
              msg: '成功'
            },
            {
              tablename: 'tbl_ddl_record',
              status: '成功',
              msg: '成功'
            },
            {
              tablename: 'tbl_ddl_rule',
              status: '成功',
              msg: '成功'
            }
          ]
        }
      ]
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!')
    },
    handrowclick(row) {
      this.$refs.expandmigtable.handleExpandClick(row)
    },
    handjobrecordrowclick(row) {
      this.$refs.expandjobrecodetable.handleExpandClick(row)
    },
    cellStyle(row) {
      if (row.status === '失败') {
        return 'color:red'
      } else if (row.status === '执行中') {
        return 'color:#a7490b'
      } else {
        return 'color:green'
      }
    }
  },
  mounted() {
    const row = this.$route.query
    Object.keys(this.form).map(v => {
      this.form[v] = row[v]
    })
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
.strcss {
  display: flex;
  // text-align: center;
  font-size: 16px;
}
.itemcss {
  width: 50%;
}
.inputcss {
  width: 450px;
}

.retcss {
  display: flex;
  text-align: center;
  font-size: 16px;
  font-weight: bold;
}
.suc {
  background-color: #0fec46;
  width: 20px;
  margin: 0px 10px 0px 0px;
}
.fail {
  background-color: red;
  width: 20px;
  margin: 0px 10px 0px 0px;
}
.doing {
  background-color: #eccb0f;
  width: 20px;
  margin: 0px 10px 0px 0px;
}
</style>