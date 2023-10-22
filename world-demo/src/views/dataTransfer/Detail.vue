<!--
 * @Author: yanchenxi
 * @Date: 2022-06-13 20:00:15
 * @LastEditTime: 2022-08-09 15:39:15
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\dataTransfer\Detail.vue
-->
<template>
  <div>
    <bfe-form ref="form" :model="form" label-width="150px">
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="任务名称">
            <bfe-input v-model="form.a1" disabled></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="任务类型">
            <bfe-select v-model="form.a2" placeholder="请选择" disabled>
              <bfe-option label="全量" value="shanghai"></bfe-option>
              <bfe-option label="增量" value="beijing"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="迁移数据源类型">
            <bfe-select v-model="form.a5" placeholder="请选择" disabled>
              <bfe-option label="ORACLE" value="oracle"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="目标数据源类型">
            <bfe-select v-model="form.a6" placeholder="请选择" disabled>
              <bfe-option label="TDSQL" value="tdsql"></bfe-option>
              <bfe-option label="MYSQL" value="mysql"></bfe-option>
            </bfe-select>
          </bfe-form-item>
        </div>
      </div>
      <div class="row">
        <div class="col col-md-5">
          <bfe-form-item label="迁移数据源名称">
            <bfe-input v-model="form.a3" disabled></bfe-input>
          </bfe-form-item>
        </div>
        <div class="col col-md-5">
          <bfe-form-item label="目标数据源名称">
            <bfe-input v-model="form.a4" disabled></bfe-input>
          </bfe-form-item>
        </div>
      </div>
    </bfe-form>
    <div class="companyList-ctn" style="width: 1000px; height: 380px;">
      <div :id="id" :style="{'width':'100%','height': '300px'}"></div>
    </div>
    <box-card />
  </div>
</template>

<script>
import boxCard from './components/BoxCard.vue';
//创建echarts的初始化id
let _c = { id: 1 };
//导入echarts包，导入echarts包使用require,为何不使用import
let echarts = require('echarts');
export default {
  components: { boxCard },
  created() {
    _c.id++;
    this.id = 'c_pie_' + _c.id; //动态创建饼图的id选择器，由于id的唯一性
  },
  props: {
    dialogVisible: {
      type: Boolean,
      default: false
    },
    dialogData: {
      type: Object,
      default: () => {}
    },
    pieData: {
      type: Object
    }
  },
  data() {
    return {
      isShow: this.dialogVisible,
      form: {
        a1: '全量迁移',
        a2: 'shanghai',
        a3: '源数据源1',
        a4: '目标数据源1',
        a5: 'ORACLE',
        a6: 'tdsql'
      },
      myPieChart: '', //声明一个变量来接收echarts初始化的实例
      width: '100%',
      height: 300 + 'px'
    };
  },
  mounted() {
    console.log('test'),
      // 初始化echarts
      this.$nextTick(() => {
        this.initChart();
      });
    // 监听浏览器窗口变化初始化echarts，也就是当浏览器可视区域改变，让图表自适应
    window.addEventListener('resize', this.initChart, false);
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.initChart);
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
    initChart() {
      //获取dom插入echarts图表
      var chartDom = document.getElementById(this.id);
      //在每次初始化实例之前先清除上一次创建好的实例，以便数据是最新的，避免数据错乱
      if (
        this.myPieChart != null &&
        this.myPieChart != '' &&
        this.myPieChart != undefined
      ) {
        this.myPieChart.dispose(); //销毁
      }
      //echarts初始化方法
      this.myPieChart = echarts.init(chartDom);
      //let _this=this
      // 监听页面变化图表自适应
      this.myPieChart.resize();
      //echarts配置项
      var option = {
        tooltip: {
          //提示框浮层的位置，
          trigger: 'item' //默认不设置时位置会跟随鼠标的位置。
        },
        legend: {
          //饼图图例配置项
          // 类目位置
          orient: 'vertical', //类目排列显示
          top: 'center', //类目位置
          left: 40 + '%', //类目位置
          icon: 'circle', //类目形状，默认圆角矩形
          itemWidth: 10, //类目原型宽度
          itemHeight: 10, //类目原型高度
          textStyle: {
            lineHeight: 15,
            fontSize: 14,
            color: '#333333'
          },
          data: [
            //类目数据
            '迁移成功+验证通过',
            '迁移成功+验证失败',
            '迁移中',
            '迁移失败'
          ]
        },
        //饼图数据配置项
        series: [
          {
            type: 'pie', //echarts类型指定，这个type决定当前的图表是饼图还是折线图亦或是柱状图
            radius: '70%', //饼图大小，传数组显示环形,
            center: ['20%', '50%'], //饼图位置,展示在父容器内的位置
            selectedMode: 'multiple', //选中模式，表示是否支持多个选中，默认关闭，支持布尔值和字符串，字符串取值可选,‘single’,‘multiple’,分别表示单选还是多选。
            itemStyle: {
              //饼图样式，由于饼图只有一项数据，所以只有一个itemStyle，稍后我们会介绍柱状图，会有多个itemStyle
              color: function(params) {
                //设置饼图颜色-渐变
                var colorList = [
                  { c1: '#2DC4FF', c2: '#1492FF' },
                  { c1: '#53DEC4', c2: '#29BA91' },
                  { c1: '#F77392', c2: '#EC3F59' },
                  { c1: '#FFEB3B', c2: 'yellow' }
                ];
                // 设置饼图渐变色
                return new echarts.graphic.LinearGradient(1, 0, 0, 0, [
                  {
                    //颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                    offset: 0,
                    color: colorList[params.dataIndex].c1
                  },
                  {
                    offset: 1,
                    color: colorList[params.dataIndex].c2
                  }
                ]);
              }
            },
            label: {
              //饼图图形上的文本标签，可用于说明图形的一些数据信息，比如值，名称等
              //show: this.pieData.lableLine,//是否显示饼图上的指示线
              formatter: '{c}' //饼图上展示数值
            },
            data: [
              //饼图数据
              {
                name: '迁移成功+验证通过',
                value: 10
              },
              { name: '迁移成功+验证失败', value: 2 },
              { name: '迁移中', value: 2 },
              { name: '迁移失败', value: 2 }
            ],
            emphasis: {
              //高亮状态的扇区和标签样式。（鼠标移入时，饼图的样式配置项，可设置阴影样式，以及文字加粗颜色还有文字大小等等）
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      this.myPieChart.setOption(option); //setOption这个api是echarts内置的api
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
.companyList-ctn {
  width: 260px;
  border-radius: 4px;
  margin-bottom: 10px;
  background: white;
  .companyList-oneItem {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
}
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: '';
}
.clearfix:after {
  clear: both;
}

.box-card {
  width: 800px;
}
</style>