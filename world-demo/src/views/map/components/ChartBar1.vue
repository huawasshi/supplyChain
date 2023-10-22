<!--
 * @Author: yanchenxi
 * @Date: 2021-11-19 13:46:30
 * @LastEditTime: 2022-11-07 11:17:19
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \yanshi\src\views\map\components\ChartBar1.vue
-->
<template>
  <div class="com-container">
    <div class="bar-title">{{title}}</div>
    <!-- <div class="title">活跃度</div> -->
    <div class="bar-content">
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
    ptExpndtrplns: {
      type: Array,
      default: () => []
    }
  },
  watch: {
    ptExpndtrplns: {
      handler() {
        this.initChart();
      },
      deep: true,
      immediate: true
    }
  },
  data() {
    return {
      chartIntance: null,
      timer: null,
      dataX: [],
      dataY: []
    };
  },
  methods: {
    initChart() {
      // 基于准备好的dom，初始化echarts实例
      this.chartIntance = echarts.init(this.$refs.chartsBar);
      const [startValue, endValue] = [0, 3];
      this.dataX = this.ptExpndtrplns.map(v => v.payPurpose);
      this.dataY = this.ptExpndtrplns.map(v => v.value);
      const option = {
        tooltip: {},
        color: ['#0487ed', '#42e2fa', '#dcac45', '#cd6237'],
        // title: {
        //   // text: '活跃度',
        //   x: 'left',
        //   textStyle: {
        //     color: '#fff',
        //     // padding: [-20, 0, 10, 10],
        //     fontSize: 12
        //   }
        // },
        xAxis: {
          type: 'category',
          // data: ['eqw', 'eqwewq'],
          data: this.dataX,
          show: true,
          axisLine: {
            lineStyle: {
              color: '#4672cf' // 横坐标轴的颜色
            }
          }
        },
        dataZoom: {
          show: false,
          startValue,
          endValue
        },
        yAxis: {
          type: 'value',
          minInterval: 1,

          formatter: function(value) {
            var str = value.split('');
            return str.join('\n');
          },
          axisLabel: {
            interval: 0,
            textStyle: {
              color: '#fff'
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
          },
          splitArea: {
            show: false
          }
        },
        legend: {
          data: ['进', '出'],
          right: 20,
          textStyle: {
            color: '#959ad8'
          }
        },
        series: [
          {
            data: this.dataY,
            name: '进',

            // data: [22, 11],
            type: 'bar',
            // barCategoryGap: '1%',
            barGap: 0,
            showBackground: true,
            backgroundStyle: {
              color: '#0f1c48'
            },
            itemStyle: {
              normal: {
                label: {
                  // show: true,
                  // position: 'insideRight',
                  textStyle: {
                    fontSize: '16',
                    color: '#42e2fa'
                  }
                }
                // barBorderRadius: [5, 5, 5, 5]
              }
            }
          }
        ],
        grid: {
          top: '12%',
          left: '8%',
          right: '3%',
          bottom: '14%',
          containLable: true // 坐标轴文字包含在left\right等里面
        }
      };
      this.chartIntance.setOption(option); // 绘制图表
      this.startTimer();
      console.log(this.timer, '-----------------------------------------');
      this.chartIntance.on('mouseover', this.endTimer);
      this.chartIntance.on('mouseout', this.startTimer);
    },
    screenAdapter() {
      this.chartIntance.resize();
    },
    startTimer() {
      this.timer = setInterval(() => {
        const item1 = this.dataX.shift();
        this.dataX.push(item1);
        const item2 = this.dataY.shift();
        this.dataY.push(item2);
        this.chartIntance.setOption({
          series: {
            data: this.dataY,
            type: 'bar'
          },
          xAxis: {
            type: 'category',
            data: this.dataX
          }
        });
      }, 2000);
    },
    endTimer() {
      clearInterval(this.timer);
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
.bar-title {
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
.bar-content {
  height: 73%;
  position: absolute;
  width: 96%;
  top: 19%;
  margin: 2%;
  overflow: hidden;
  padding: 2%;
}
.bar-content .com-chart {
  height: 100%;
  width: 100%;
}
</style>
