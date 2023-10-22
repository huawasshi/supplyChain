<!--
 * @Author: yanchenxi
 * @Date: 2021-11-19 13:46:30
 * @LastEditTime: 2022-08-16 18:46:30
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \yanshi\src\views\map\components\ChartPie1.vue
-->
<template>
  <div class="com-container">
    <div class="pie-title">{{title}}</div>
    <div class="pie-content">
      <!-- <div class="pie-box"></div> -->
      <div ref="chartsBar" class="com-chart"></div>
    </div>
  </div>
</template>

<script>
import echarts from 'echarts';
export default {
  name: 'chartPie',
  props: {
    title: {
      type: String,
      default: ''
    },
    tranTypePers: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      chartIntance: null
    };
  },
  watch: {
    tranTypePers: {
      handler() {
        this.drawPie(this.tranTypePers);
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    screenAdapter() {
      this.chartIntance.resize();
    },
    drawPie(tranTypePers) {
      // 基于准备好的dom，初始化echarts实例
      this.chartIntance = echarts.init(this.$refs.chartsBar);
      // 绘制图表
      let option = {
        tooltip: {
          trigger: 'item',
          textStyle: {
            fontSize: 12
            // color: '#289df5'
          },
          formatter: '{b}:{c} ({d}%)' // 悬浮显示设置
        },
        color: ['#0487ed', '#42e2fa', '#dcac45', '#cd6237'], // 默认颜色，目前只定义了四种颜色，如果不够，就使用组件自带默认随机颜色
        legend: {
          orient: 'vertical', // 排列方式
          right: 20,
          type: 'scroll',
          top: 'middle',
          itemGap: 20,
          textStyle: {
            color: '#959ad8'
          }
          // top: 100, // 矩形文字显示位置控制
          // data: ['01001', '01002', '01003', '01004'] // 右侧小矩形文字
        },
        series: [
          {
            name: '',
            type: 'pie',
            // label: { show: false }, // 去除折线以及文字
            radius: ['30%', '90%'],
            center: ['40%', '50%'],
            // roseType: 'area',
            itemStyle: {
              borderRadius: 8,
              normal: {
                label: {
                  textStyle: {
                    // color: '#306eef'
                  }
                },
                labelLine: {}
              }
            },
            labelLine: {
              normal: {
                show: true
              }
            },
            label: {
              normal: {
                show: true,
                formatter: '{d}%'
              }
            },
            data: tranTypePers.map(v => {
              return {
                name: v.msgType,
                value: v.typeTranNum
              };
            })
          }
        ],
        grid: {
          height: '100%'
        }
      };
      this.chartIntance.setOption(option); // 绘制图表
    }
  },
  mounted() {
    // 监听窗口凡是变化，
    window.addEventListener('resize', this.screenAdapter);
    this.drawPie(this.tranTypePers);
  },
  destroyed() {
    window.removeEventListener('resize', this.screenAdapter);
  }
};
</script>

<style  scoped>
.pie-title {
  background: url('../../../assets/imgs/标题bg.png') no-repeat;
  background-size: 100% 100%;
  height: 12%;
  width: 70%;
  position: absolute;
  left: 15%;
  top: 7%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
}
.pie-content {
  height: 73%;
  position: absolute;
  width: 96%;
  top: 19%;
  margin: 2%;
  overflow: hidden;
  padding: 2%;
  display: flex;
}
.pie-content .com-chart {
  height: 100%;
  width: 100%;
}
.pie-content .pie-box {
  background: url('../../../assets/imgs/jy.png') no-repeat;
  background-size: 100% 100%;
  position: absolute;
  left: 25%;
  width: 30%;
  height: 88%;
}
</style>
