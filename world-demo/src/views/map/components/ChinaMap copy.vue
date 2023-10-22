<!--
 * @Author: yanchenxi
 * @Date: 2021-03-05 13:39:32
 * @LastEditTime: 2022-08-16 20:30:40
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \yanshi\src\views\map\components\ChinaMap.vue
-->
<template>
  <div class="com-container">
    <div class="pie-title">{{title}}</div>
    <div class="pie-content">
      <div ref="chinaMap" class="com-chart"></div>
    </div>
  </div>
</template>

<script>
import china from 'echarts/map/json/china';
import echarts from 'echarts';
echarts.registerMap('china', china);
export default {
  props: {
    title: {
      type: String,
      default: ''
    },
    ptContracts: {
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
    ptContracts: {
      handler() {
        this.init();
      },
      deep: true,
      immediate: true
    }
  },
  mounted() {
    console.log(china, '--------------------------------');
    // 监听窗口凡是变化，
    window.addEventListener('resize', this.screenAdapter);
    this.init();
  },
  methods: {
    init() {
      const list = this.ptContracts.map(v => ({
        name: v.provinceName,
        value: v.value
      }));
      this.drawMap(list);
    },
    screenAdapter() {
      this.chartIntance.resize();
    },
    drawMap(dataList) {
      console.log(dataList);
      // 基于准备好的dom，初始化echarts实例
      this.chartIntance = echarts.init(this.$refs.chinaMap);
      let option = {
        tooltip: {
          textStyle: {
            fontSize: 12,
            color: '#F7BA2A'
          },
          backgroundColor: 'rgba(18,77,144,0.5)',
          formatter: '{b}:{c}'
        },
        geo: {
          map: 'china',
          roam: false, // 关闭拖拽
          label: {
            normal: {
              show: true // 关闭省份名字显示
            }
          },
          itemStyle: {
            normal: {
              areaColor: '#0d0059',
              borderWidth: 1,
              shadowOffsetX: 0,
              shadowOffsetY: 8,
              shadowBlur: 5,
              shadowColor: '#01012a'
            }
          }
        },
        visualMap: {
          show: false,
          min: 0,
          // max: 5000,
          left: '',
          text: ['高', '低'],
          realtime: false,
          calculable: true,
          color: ['#50BFFF', '#011a89']
        },
        series: [
          {
            name: '',
            type: 'map',
            mapType: 'china',
            itemStyle: {
              normal: {
                areaColor: '#0d0059',
                borderColor: '#398dff',
                borderWidth: 0.5
              },
              emphasis: {
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                shadowBlur: 5,
                borderWidth: 0,
                // shadowColor: 'rgba(0,0,0,0.5)'
                shadowColor: '#F7BA2A'
              }
            },
            label: {
              normal: {
                show: false,
                textStyle: {
                  // 字体颜色大小
                  color: '#052556'
                }
              },
              emphasis: {
                show: true
              }
            },
            data: dataList
            // data: [
            //   { name: '新疆', value: 120 },
            //   { name: '吉林', value: 101 },
            //   { name: '辽宁', value: 311 },
            //   { name: '河北', value: 13 },
            //   { name: '天津', value: 200 },
            //   { name: '北京', value: 515 },
            //   { name: '山西', value: 16 },
            //   { name: '陕西', value: 617 },
            //   { name: '山东', value: 180 },
            //   { name: '河南', value: 419 },
            //   { name: '江苏', value: 20 },
            //   { name: '上海', value: 12 },
            //   { name: '安徽', value: 840 },
            //   { name: '浙江', value: 712 },
            //   { name: '江西', value: 12 },
            //   { name: '湖北', value: 140 },
            //   { name: '湖南', value: 230 },
            //   { name: '四川', value: 12 },
            //   { name: '贵州', value: 212 },
            //   { name: '重庆', value: 512 },
            //   { name: '海南', value: 60 },
            //   { name: '台湾', value: 12 },
            //   { name: '香港', value: 412 },
            //   { name: '澳门', value: 212 },
            //   { name: '广东', value: 890 },
            //   { name: '福建', value: 712 },
            //   { name: '广西', value: 12 },
            //   { name: '云南', value: 100 },
            //   { name: '西藏', value: 128 },
            //   { name: '青海', value: 712 },
            //   { name: '甘肃', value: 612 },
            //   { name: '宁夏', value: 1130 },
            //   { name: '内蒙古', value: 12 },
            //   { name: '黑龙江', value: 12 }
            // ]
          }
        ],
        grid: {
          top: '0%',
          left: '8%',
          right: '3%',
          bottom: '0%',
          containLable: true // 坐标轴文字包含在left\right等里面
        }
      };
      this.chartIntance.setOption(option); // 绘制图表
    }
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
  height: 6%;
  width: 70%;
  position: absolute;
  left: 15%;
  top: 4%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
}
.pie-content {
  height: 91%;
  position: absolute;
  width: 96%;
  top: 3%;
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
