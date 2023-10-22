<template>
  <div class="companyList-ctn" :style="{ 'width': width }">
    <div :id="id" :style="{'width':'100%','height': height}"></div>
  </div>
</template>
<script>
//创建echarts的初始化id
let _c = { id: 1 };
//导入echarts包，导入echarts包使用require,为何不使用import
let echarts = require("echarts");
export default {
  created() {
    _c.id++;
    this.id = "c_pie_" + _c.id;//动态创建饼图的id选择器，由于id的唯一性
  },
  props: {
    pieData: {
      type: Object,
    },
  },
  data() {
    return {
      myPieChart:'',//声明一个变量来接收echarts初始化的实例
      width:'100%',
      height:300+'px'
    };
  },
  mounted() {
    // 初始化echarts
    this.$nextTick(()=>{
      this.initChart();
    })
    // 监听浏览器窗口变化初始化echarts，也就是当浏览器可视区域改变，让图表自适应
    window.addEventListener('resize',this.initChart,false);
  },
  //vue组件实例销毁之前移除监听事件，避免当我们切换路由时导致vue出现警告：
  //echarts.js?1be7:2160 Uncaught Error: Initialize failed: invalid dom.
  beforeDestroy () {
    window.removeEventListener('resize', this.initChart)
  },
  methods: {
    initChart() {
      //获取dom插入echarts图表
      var chartDom = document.getElementById(this.id);
      //在每次初始化实例之前先清除上一次创建好的实例，以便数据是最新的，避免数据错乱
      if (this.myPieChart != null && this.myPieChart != "" && this.myPieChart != undefined) {
        this.myPieChart.dispose();//销毁
      }
      //echarts初始化方法
      this.myPieChart = echarts.init(chartDom);
      let _this=this
      // 监听页面变化图表自适应
      this.myPieChart.resize()
     //echarts配置项
      var option = {
        tooltip: {//提示框浮层的位置，
          trigger: "item",//默认不设置时位置会跟随鼠标的位置。
        },
        legend:{//饼图图例配置项
          // 类目位置
          orient: "vertical", //类目排列显示
          top: "center", //类目位置
          left: 40 + "%", //类目位置
          icon: "circle", //类目形状，默认圆角矩形
          itemWidth: 10, //类目原型宽度
          itemHeight: 10, //类目原型高度
          textStyle: {
            lineHeight: 15,
            fontSize: 14,
            color: "#333333",
          },
          data: [
            //类目数据
            "企业投资项目备案表/政府投资项目可行性研究报告批复",
            "建设用地规划许可证",
            "建设工程设计方案确认书",
            "政府投资项目初步设计批复",
            "建设工程规划许可证",
            "建筑工程施工许可证",
          ],
        },
        //饼图数据配置项
        series: [
          {
            type: "pie",//echarts类型指定，这个type决定当前的图表是饼图还是折线图亦或是柱状图
            radius:"70%", //饼图大小，传数组显示环形,
            center:["20%", "50%"], //饼图位置,展示在父容器内的位置
            selectedMode: "single",//选中模式，表示是否支持多个选中，默认关闭，支持布尔值和字符串，字符串取值可选,‘single’,‘multiple’,分别表示单选还是多选。
            itemStyle: {//饼图样式，由于饼图只有一项数据，所以只有一个itemStyle，稍后我们会介绍柱状图，会有多个itemStyle
              color: function (params) {//设置饼图颜色-渐变
                var colorList = [
                { c1: "#2DC4FF", c2: "#1492FF" },
                { c1: "#53DEC4", c2: "#29BA91" },
                { c1: "#F77392", c2: "#EC3F59" },
                { c1: "#F8DC77", c2: "#E5981D" },
                { c1: "#CC2CB7", c2: "#FF5AE6" },
                { c1: "#1FA9B7", c2: "#62FAFF" },
                 ];
                // 设置饼图渐变色
                return new echarts.graphic.LinearGradient(1, 0, 0, 0, [
                  {
                    //颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                    offset: 0,
                    color: colorList[params.dataIndex].c1,
                  },
                  {
                    offset: 1,
                    color: colorList[params.dataIndex].c2,
                  },
                ]);
              },
            },
            label: {//饼图图形上的文本标签，可用于说明图形的一些数据信息，比如值，名称等
              show: this.pieData.lableLine,//是否显示饼图上的指示线
              formatter: "{c}",//饼图上展示数值
            },
            data: [//饼图数据
                {
                  name: "企业投资项目备案表/政府投资项目可行性研究报告批复",
                  value: 24,
                },
                { name: "建设用地规划许可证", value: 31 },
                { name: "建设工程设计方案确认书", value: 19 },
                { name: "政府投资项目初步设计批复", value: 11 },
                { name: "建设工程规划许可证", value: 3 },
                { name: "建筑工程施工许可证", value: 2 },
              ],
            emphasis: {//高亮状态的扇区和标签样式。（鼠标移入时，饼图的样式配置项，可设置阴影样式，以及文字加粗颜色还有文字大小等等）
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };
      this.myPieChart.setOption(option);//setOption这个api是echarts内置的api
    },
  },
};
</script>
<style lang="scss" scoped>
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
</style>