<!--
 * @Author: yanchenxi
 * @Date: 2021-11-19 13:46:30
 * @LastEditTime: 2022-08-20 17:31:39
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \yanshi\src\views\map\components\ChartLine1.vue
-->
<template>
  <div class="com-container">
    <div class="line-title">{{title}}</div>
    <div class="title">速率 ( MB/S )</div>
    <div class="line-content">
      <div ref="chartsBar" class="com-chart"></div>
    </div>
  </div>
</template>

<script>
import echarts from 'echarts';
export default {
  name: 'chartsBar',
  props: {
    title: {
      type: String,
      default: ''
    },
    lastWeekTranStats: {
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
    lastWeekTranStats: {
      handler() {
        this.initChart();
      },
      deep: true,
      immediate: true
    }
  },
  methods: {
    initChart() {
      // 基于准备好的dom，初始化echarts实例
      this.chartIntance = echarts.init(this.$refs.chartsBar);
      console.log(this.lastWeekTranStats);
      const option = {
        tooltip: {},
        // title: {
        //   text: '笔款',
        //   x: 'left',
        //   textStyle: {
        //     color: '#fff',
        //     padding: [-20, 0, 10, 10],
        //     fontSize: 12
        //   }
        // },
        xAxis: {
          type: 'category',
          data: this.lastWeekTranStats.map(v => v.tranDate),
          axisLabel: {
            textStyle: {
              color: '#fff'
            }
          },
          splitArea: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: '#4672cf' // 横坐标轴的颜色
            }
          }
        },
        yAxis: {
          type: 'value',
          minInterval: 1,

          axisLabel: {
            textStyle: {
              color: '#fff' // 纵坐标字体的颜色
            }
          },
          splitLine: {
            lineStyle: {
              color: '#052556' // 纵坐标横向的线的颜色
            }
          },
          axisLine: {
            lineStyle: {
              color: '#4672cf' // 纵坐标轴的颜色
            }
          }
        },

        series: [
          {
            data: this.lastWeekTranStats.map(v => v.dayTranNum),
            type: 'line',
            name: 'afmsBC_node1',
            itemStyle: {
              normal: {
                color: '#2196F3',
                lineStyle: {
                  color: '#2196F3'
                }
              }
            }
          },
          {
            data: this.lastWeekTranStats.map(v => v.a1),
            type: 'line',
            name: 'afmsBC_node2',
            itemStyle: {
              normal: {
                color: '#4CAF50',
                lineStyle: {
                  color: '#4CAF50'
                }
              }
            }
          },
          {
            data: this.lastWeekTranStats.map(v => v.a2),
            type: 'line',
            name: 'afmsBC_node3',
            itemStyle: {
              normal: {
                color: '#FF9800',
                lineStyle: {
                  color: '#FF9800'
                }
              }
            }
          },
          {
            data: this.lastWeekTranStats.map(v => v.a3),
            type: 'line',
            name: 'afmsBC_node4',
            // symbolSize: 8,
            itemStyle: {
              normal: {
                color: '#42e2fa',
                lineStyle: {
                  color: '#42e2fa'
                }
              }
            }
          }
        ],

        grid: {
          top: '5%',
          left: '8%',
          right: '3%',
          bottom: '15%',
          containLable: true // 坐标轴文字包含在left\right等里面
        }
      };
      this.chartIntance.setOption(option); // 绘制图表
    },
    screenAdapter() {
      this.chartIntance.resize();
    }
  },
  mounted() {
    window.addEventListener('resize', this.screenAdapter);
    this.initChart();
  },
  destroyed() {
    clearInterval(this.timer);
    window.removeEventListener('resize', this.screenAdapter);
  }
};
</script>

<style  scoped>
.title {
  position: absolute;
  top: 20%;
  left: 5%;
  font-size: 12px;
}
.line-title {
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
.line-content {
  height: 73%;
  position: absolute;
  width: 96%;
  top: 19%;
  margin: 2%;
  overflow: hidden;
  padding: 2%;
}
.line-content .com-chart {
  height: 100%;
  width: 100%;
}
</style>
