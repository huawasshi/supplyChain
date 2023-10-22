<!--
 * @Author: yanchenxi
 * @Date: 2021-03-05 13:39:32
 * @LastEditTime: 2022-08-20 17:05:29
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \yanshi\src\views\test\test4\ChinaMap.vue
-->
<template>
  <div class="com-container">
    <div class="pie-content">
      <div ref="chinaMap" class="com-chart"></div>
    </div>
  </div>
</template>

<script>
import china from 'echarts/map/json/china';
import echarts from 'echarts';
// import { img } from '../img';
echarts.registerMap('china', china);
var geoCoordMap = {
  上海: [121.4648, 31.2891],
  东莞: [113.8953, 22.901],
  东营: [118.7073, 37.5513],
  中山: [113.4229, 22.478],
  临汾: [111.4783, 36.1615],
  临沂: [118.3118, 35.2936],
  丹东: [124.541, 40.4242],
  丽水: [119.5642, 28.1854],
  乌鲁木齐: [87.9236, 43.5883],
  佛山: [112.8955, 23.1097],
  保定: [115.0488, 39.0948],
  兰州: [103.5901, 36.3043],
  包头: [110.3467, 41.4899],
  北京: [116.4551, 40.2539],
  北海: [109.314, 21.6211],
  南京: [118.8062, 31.9208],
  南宁: [108.479, 23.1152],
  南昌: [116.0046, 28.6633],
  南通: [121.1023, 32.1625],
  厦门: [118.1689, 24.6478],
  台州: [121.1353, 28.6688],
  合肥: [117.29, 32.0581],
  呼和浩特: [111.4124, 40.4901],
  咸阳: [108.4131, 34.8706],
  哈尔滨: [127.9688, 45.368],
  唐山: [118.4766, 39.6826],
  嘉兴: [120.9155, 30.6354],
  大同: [113.7854, 39.8035],
  大连: [122.2229, 39.4409],
  天津: [117.4219, 39.4189],
  太原: [112.3352, 37.9413],
  威海: [121.9482, 37.1393],
  宁波: [121.5967, 29.6466],
  宝鸡: [107.1826, 34.3433],
  宿迁: [118.5535, 33.7775],
  常州: [119.4543, 31.5582],
  广州: [113.5107, 23.2196],
  廊坊: [116.521, 39.0509],
  延安: [109.1052, 36.4252],
  张家口: [115.1477, 40.8527],
  徐州: [117.5208, 34.3268],
  德州: [116.6858, 37.2107],
  惠州: [114.6204, 23.1647],
  成都: [103.9526, 30.7617],
  扬州: [119.4653, 32.8162],
  承德: [117.5757, 41.4075],
  拉萨: [91.1865, 30.1465],
  无锡: [120.3442, 31.5527],
  日照: [119.2786, 35.5023],
  昆明: [102.9199, 25.4663],
  杭州: [119.5313, 29.8773],
  枣庄: [117.323, 34.8926],
  柳州: [109.3799, 24.9774],
  株洲: [113.5327, 27.0319],
  武汉: [114.3896, 30.6628],
  汕头: [117.1692, 23.3405],
  江门: [112.6318, 22.1484],
  沈阳: [123.1238, 42.1216],
  沧州: [116.8286, 38.2104],
  河源: [114.917, 23.9722],
  泉州: [118.3228, 25.1147],
  泰安: [117.0264, 36.0516],
  泰州: [120.0586, 32.5525],
  济南: [117.1582, 36.8701],
  济宁: [116.8286, 35.3375],
  海口: [110.3893, 19.8516],
  淄博: [118.0371, 36.6064],
  淮安: [118.927, 33.4039],
  深圳: [114.5435, 22.5439],
  清远: [112.9175, 24.3292],
  温州: [120.498, 27.8119],
  渭南: [109.7864, 35.0299],
  湖州: [119.8608, 30.7782],
  湘潭: [112.5439, 27.7075],
  滨州: [117.8174, 37.4963],
  潍坊: [119.0918, 36.524],
  烟台: [120.7397, 37.5128],
  玉溪: [101.9312, 23.8898],
  珠海: [113.7305, 22.1155],
  盐城: [120.2234, 33.5577],
  盘锦: [121.9482, 41.0449],
  石家庄: [114.4995, 38.1006],
  福州: [119.4543, 25.9222],
  秦皇岛: [119.2126, 40.0232],
  绍兴: [120.564, 29.7565],
  聊城: [115.9167, 36.4032],
  肇庆: [112.1265, 23.5822],
  舟山: [122.2559, 30.2234],
  苏州: [120.6519, 31.3989],
  莱芜: [117.6526, 36.2714],
  菏泽: [115.6201, 35.2057],
  营口: [122.4316, 40.4297],
  葫芦岛: [120.1575, 40.578],
  衡水: [115.8838, 37.7161],
  衢州: [118.6853, 28.8666],
  西宁: [101.4038, 36.8207],
  西安: [109.1162, 34.2004],
  贵阳: [106.6992, 26.7682],
  连云港: [119.1248, 34.552],
  邢台: [114.8071, 37.2821],
  邯郸: [114.4775, 36.535],
  郑州: [113.4668, 34.6234],
  鄂尔多斯: [108.9734, 39.2487],
  重庆: [107.7539, 30.1904],
  金华: [120.0037, 29.1028],
  铜川: [109.0393, 35.1947],
  银川: [106.3586, 38.1775],
  镇江: [119.4763, 31.9702],
  长春: [125.8154, 44.2584],
  长沙: [113.0823, 28.2568],
  长治: [112.8625, 36.4746],
  阳泉: [113.4778, 38.0951],
  青岛: [120.4651, 36.3373],
  韶关: [113.7964, 24.7028]
};

var XAData = [
  // [
  //   {
  //     name: '合肥'
  //   },
  //   {
  //     name: '北京',
  //     value: 100
  //   }
  // ],
  // [
  //   {
  //     name: '合肥'
  //   },
  //   {
  //     name: '上海',
  //     value: 100
  //   }
  // ],
  // [
  //   {
  //     name: '北京'
  //   },
  //   {
  //     name: '上海',
  //     value: 100
  //   }
  // ],
  // [
  //   {
  //     name: '合肥'
  //   },
  //   {
  //     name: '广州',
  //     value: 100
  //   }
  // ],
  // [
  //   {
  //     name: '合肥'
  //   },
  //   {
  //     name: '西宁',
  //     value: 100
  //   }
  // ],
  // [
  //   {
  //     name: '合肥'
  //   },
  //   {
  //     name: '拉萨',
  //     value: 100
  //   }
  // ],
  // [
  //   {
  //     name: '合肥'
  //   },
  //   {
  //     name: '西安',
  //     value: 100
  //   }
  // ],
  [
    {
      name: '合肥'
    },
    {
      name: '乌鲁木齐',
      value: 100,
      op: {
        a1: '12312321',
        a2: '38.7ms',
        a3: '2022-07-01',
        a4: '2℃'
      }
    }
  ]
];

var planePath =
  'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';
//var planePath = 'arrow';
// var planePath =
// 'path://M896 469.333333v298.666667h-89.6c-8.533333 25.6-34.133333 42.666667-59.733333 42.666667s-51.2-17.066667-59.733334-42.666667H337.066667c-8.533333 25.6-34.133333 42.666667-59.733334 42.666667s-51.2-17.066667-59.733333-42.666667H128V256h597.333333v128h85.333334l85.333333 85.333333zM213.333333 341.333333v341.333334h426.666667V341.333333H213.333333z m512 128v213.333334h85.333334v-170.666667l-42.666667-42.666667h-42.666667z';
var color = ['#FF9800', '#FF9800', '#fff'];
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
      chartIntance: null,
      series: []
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
    convertData(data) {
      var res = [];
      for (var i = 0; i < data.length; i++) {
        var dataItem = data[i];

        var fromCoord = geoCoordMap[dataItem[0].name];
        var toCoord = geoCoordMap[dataItem[1].name];
        if (fromCoord && toCoord) {
          res.push({
            fromName: dataItem[0].name,
            toName: dataItem[1].name,
            coords: [fromCoord, toCoord],
            value: dataItem[1].value,
            op: dataItem[1].op
          });
        }
      }
      return res;
    },
    init() {
      let that = this;
      [['合肥', XAData]].forEach(function(item, i) {
        that.series.push(
          {
            //红色线
            type: 'lines',
            zlevel: 1, //zlevel 大的 Canvas 会放在 zlevel 小的 Canvas 的上面
            effect: {
              show: true,
              period: 6,
              trailLength: 0.7,
              color: 'red', //arrow箭头的颜色
              symbolSize: 3
            },
            lineStyle: {
              normal: {
                color: color[i],
                width: 0,
                curveness: 0.2
              }
            },
            data: that.convertData(item[1])
          },
          {
            //动画连接线
            type: 'lines',
            zlevel: 2,
            symbol: ['none', 'arrow'], //贴花的图案，如果是 string[] 表示循环使用数组中的图案。'circle', 'rect', 'roundRect', 'triangle', 'diamond', 'pin', 'arrow', 'none'
            symbolSize: 10,
            effect: {
              show: true,
              period: 6, //特效动画的时间，单位为 s。
              trailLength: 0, //特效尾迹的长度。取从 0 到 1 的值，数值越大尾迹越长。
              symbol: planePath,
              symbolSize: 15 //特效标记的大小，可以设置成诸如 10 这样单一的数字，也可以用数组分开表示高和宽，例如 [20, 10] 表示标记宽为20，高为10。
            },
            lineStyle: {
              normal: {
                color: color[i],
                width: 1,
                opacity: 0.6,
                curveness: 0.2 //边的曲度，支持从 0 到 1 的值，值越大曲度越大。
              }
            },
            data: that.convertData(item[1])
          },
          {
            //顶点
            type: 'effectScatter',
            coordinateSystem: 'geo',
            zlevel: 2,
            rippleEffect: {
              brushType: 'stroke'
            },
            label: {
              normal: {
                show: true,
                position: 'right',
                formatter: '{b}'
              }
            },
            symbolSize: function(val) {
              return val[2] / 8;
            },
            itemStyle: {
              normal: {
                color: color[i]
              },
              emphasis: {
                areaColor: '#2B91B7'
              }
            },
            data: item[1].map(function(dataItem) {
              return {
                name: dataItem[1].name,
                // 举例格式：{"name":"北京","value":[116.4551,40.2539,100]}
                value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value]) //格式是传给formatter的
              };
            })
          }
        );
      });
      this.drawMap();
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
          trigger: 'item',
          formatter: function(params) {
            if (params.seriesType == 'effectScatter') {
              console.log(JSON.stringify(params));
              return '线路：' + params.data.name + '' + params.data.value[2];
            } else if (params.seriesType == 'lines') {
              return (
                params.data.fromName +
                '--->' +
                params.data.toName +
                '<br/>' +
                `订单号：${params.data.op.a1}` +
                '<br/>' +
                `时延：${params.data.op.a2}` +
                '<br/>' +
                `预计到达时间：${params.data.op.a3}` +
                '<br/>' +
                `车内温度：${params.data.op.a4}` +
                '<br />' +
                `当前位置：东经${params.data.coords[0][0]},北纬${
                  params.data.coords[0][1]
                }`
              );
            } else {
              return params.name;
            }
          }
        },

        geo: {
          map: 'china',
          label: {
            emphasis: {
              show: true,
              color: '#fff'
            }
          },
          roam: false,
          //   放大我们的地图
          zoom: 1,
          itemStyle: {
            normal: {
              areaColor: 'rgba(43, 196, 243, 0.42)',
              borderColor: 'rgba(43, 196, 243, 1)',
              borderWidth: 1
            },
            emphasis: {
              areaColor: '#2B91B7'
            }
          }
        },

        series: this.series
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
  height: 100%;
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
