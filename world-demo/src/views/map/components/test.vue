<!--
 * @Author: yanchenxi
 * @Date: 2020-12-08 14:39:12
 * @LastEditTime: 2022-06-07 15:16:13
 * @LastEditors: yanchenxi
 * @Description: 公共饼状图
 * @FilePath: \pamsFront\src\pages\map\components\test.vue
-->
<template>
  <div class="com-container">
    <title-tip msg="交易量"></title-tip>
    <div ref="situationPie" class="com-chart"></div>
  </div>
</template>

<script>
import titleTip from '../components/title';
var dataMap = {};
function dataFormatter(obj) {
  var pList = [
    '1月',
    '2月',
    '3月',
    '4月',
    '5月',
    '6月',
    '7月',
    '8月',
    '9月',
    '10月',
    '11月',
    '12月'
  ];
  var temp;
  for (var year = 2012; year <= 2021; year++) {
    var max = 0;
    var sum = 0;
    temp = obj[year];
    for (var i = 0, l = temp.length; i < l; i++) {
      max = Math.max(max, temp[i]);
      sum += temp[i];
      obj[year][i] = {
        name: pList[i],
        value: temp[i]
      };
    }
    obj[year + 'max'] = Math.floor(max / 100) * 100;
    obj[year + 'sum'] = sum;
  }
  return obj;
}

dataMap.dataGDP = dataFormatter({
  // max : 60000,
  2012: [
    16251.93,
    11307.28,
    24515.76,
    11237.55,
    14359.88,
    22226.7,
    10568.83,
    12582,
    19195.69,
    49110.27,
    32318.85,
    32318.85
  ],
  2013: [
    14113.58,
    9224.46,
    20394.26,
    9200.86,
    11672,
    18457.27,
    8667.58,
    10368.6,
    17165.98,
    41425.48,
    27722.31,
    12359.33
  ],
  2014: [
    12153.03,
    7521.85,
    17235.48,
    7358.31,
    9740.25,
    15212.49,
    7278.75,
    8587,
    15046.45,
    34457.3,
    22990.35,
    10062.82
  ],
  2015: [
    11115,
    6719.01,
    16011.97,
    7315.4,
    8496.2,
    13668.58,
    6426.1,
    8314.37,
    14069.87,
    30981.98,
    21462.69,
    8851.66
  ],
  2016: [
    9846.81,
    5252.76,
    13607.32,
    6024.45,
    6423.18,
    11164.3,
    5284.69,
    7104,
    12494.01,
    26018.48,
    18753.73,
    7360.92
  ],
  2017: [
    8117.78,
    4462.74,
    11467.6,
    4878.61,
    4944.25,
    9304.52,
    4275.12,
    6211.8,
    10572.24,
    21742.05,
    15718.47,
    6112.5
  ],
  2018: [
    6969.52,
    3905.64,
    10012.11,
    4230.53,
    3905.03,
    8047.26,
    3620.27,
    5513.7,
    9247.66,
    18598.69,
    13417.68,
    5350.17
  ],
  2019: [
    6033.21,
    3110.97,
    8477.63,
    3571.37,
    3041.07,
    6672,
    3122.01,
    4750.6,
    8072.83,
    15003.6,
    11648.7,
    4759.3
  ],
  2020: [
    5007.21,
    2578.03,
    6921.29,
    2855.23,
    2388.38,
    6002.54,
    2662.08,
    4057.4,
    6694.23,
    12442.87,
    9705.02,
    3923.11
  ],
  2021: [
    4315,
    2150.76,
    6018.28,
    2324.8,
    1940.94,
    5458.22,
    2348.54,
    3637.2,
    5741.03,
    10606.85,
    8003.67,
    3519.72
  ]
});

export default {
  name: 'chartPie',
  components: { titleTip },
  data() {
    return {
      chartIntance: null
    };
  },
  methods: {
    screenAdapter() {
      this.chartIntance.resize();
    },
    drawPie() {
      // 基于准备好的dom，初始化echarts实例
      this.chartIntance = this.$echarts.init(this.$refs.situationPie);
      // 绘制图表
      let option = {
        baseOption: {
          color: '#50BFFF',
          timeline: {
            axisType: 'category',
            left: '3%',
            right: '3%',
            // realtime: false,
            // loop: false,
            autoPlay: true,
            // currentIndex: 2,
            playInterval: 1000,
            // controlStyle: {
            //     position: 'left'
            // },
            data: [
              '2012-01-01',
              '2013-01-01',
              '2014-01-01',
              '2015-01-01',
              '2016-01-01',
              '2017-01-01',
              '2018-01-01',
              '2019-01-01',
              '2020-01-01',
              '2021-01-01'
            ],
            label: {
              color: '#959ad8',
              lineHeight: 18,
              formatter: function(s) {
                return new Date(s).getFullYear();
              }
            }
          },
          tooltip: {},
          calculable: true,
          grid: {
            top: '3%',
            left: '10%',
            right: '2%',
            bottom: '25%',
            containLable: true, // 坐标轴文字包含在left\right等里面
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'shadow',
                label: {
                  show: true,
                  color: '#50BFFF',
                  formatter: function(params) {
                    return params.value.replace('\n', '');
                  }
                }
              }
            }
          },
          xAxis: [
            {
              type: 'category',
              data: [
                '1月',
                '2月',
                '3月',
                '4月',
                '5月',
                '6月',
                '7月',
                '8月',
                '9月',
                '10月',
                '11月',
                '12月'
              ],
              splitLine: { show: false },
              axisLabel: {
                interval: 0,
                textStyle: {
                  color: '#959ad8'
                }
              },
              axisLine: {
                lineStyle: {
                  color: '#052556' // 横坐标轴的颜色
                }
              }
            }
          ],
          yAxis: [
            {
              type: 'value',
              axisLabel: {
                textStyle: {
                  color: '#959ad8' // 纵坐标字体的颜色
                }
              },
              splitLine: {
                lineStyle: {
                  color: '#052556' // 纵坐标横向的线的颜色
                }
              },
              axisLine: {
                lineStyle: {
                  color: '#052556' // 纵坐标轴的颜色
                }
              }
            }
          ],
          series: [{ name: '交易', type: 'line' }]
        },
        options: [
          {
            series: [{ data: dataMap.dataGDP['2012'] }]
          },
          {
            series: [{ data: dataMap.dataGDP['2013'] }]
          },
          {
            series: [{ data: dataMap.dataGDP['2014'] }]
          },
          {
            series: [{ data: dataMap.dataGDP['2015'] }]
          },
          {
            series: [{ data: dataMap.dataGDP['2016'] }]
          },
          {
            series: [{ data: dataMap.dataGDP['2017'] }]
          },
          {
            series: [{ data: dataMap.dataGDP['2018'] }]
          },
          {
            series: [{ data: dataMap.dataGDP['2019'] }]
          },
          {
            series: [{ data: dataMap.dataGDP['2020'] }]
          },
          {
            series: [{ data: dataMap.dataGDP['2021'] }]
          }
        ]
      };
      this.chartIntance.setOption(option); // 绘制图表
    }
  },
  mounted() {
    // 监听窗口凡是变化，
    window.addEventListener('resize', this.screenAdapter);
    this.drawPie(this.total);
  },
  destroyed() {
    window.removeEventListener('resize', this.screenAdapter);
  }
};
</script>

<style>
</style>
