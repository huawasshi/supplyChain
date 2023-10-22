<!--
 * @Author: yanchenxi
 * @Date: 2021-03-05 13:39:32
 * @LastEditTime: 2023-10-21 15:22:14
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \world-demo\src\views\map\components\ChinaMap.vue
-->
<template>
  <div class="com-container">
    <div class="title">
      <div class="pie-title"></div>
    </div>
    <div class="pie-content">
      <div ref="chinaMap" class="com-chart"></div>
    </div>
    <div class="input">
      <el-input placeholder="Please enter ID" v-model="id">
        <div slot="append" @click="searchId">determine</div>
      </el-input>
    </div>
    <div class="detail">
      <div class="detail-title">transfer records</div>
      <div class="detail-line"></div>
      <ul>
        <li>
          <div class="detail-li-label">trade name</div>
          <div class="detail-li-value" :title="tablelist.name">{{tablelist.name}}</div>
        </li>
        <li>
          <div class="detail-li-label">Manufacture date</div>
          <div
            class="detail-li-value"
            :title="tablelist.manufactureDate"
          >{{tablelist.manufactureDate}}</div>
        </li>
        <li>
          <div class="detail-li-label">manufacturer</div>
          <div class="detail-li-value" :title="tablelist.manufactor">{{tablelist.manufactor}}</div>
        </li>
        <li>
          <div class="detail-li-label">Manufacturer’s responsible person</div>
          <div class="detail-li-value" :title="tablelist.person">{{tablelist.person}}</div>
        </li>
        <li>
          <div class="detail-li-label">Manufacturer’s phone number</div>
          <div class="detail-li-value" :title="tablelist.telephone">{{tablelist.telephone}}</div>
        </li>
        <li>
          <div class="detail-li-label">shelf life</div>
          <div class="detail-li-value" :title="tablelist.validityDate">{{tablelist.validityDate}}</div>
        </li>
        <li>
          <div class="detail-li-label">batch number</div>
          <div class="detail-li-value" :title="tablelist.batchNo">{{tablelist.batchNo}}</div>
        </li>
        <li>
          <div class="detail-li-label">date of launch</div>
          <div class="detail-li-value" :title="tablelist.listingDate">{{tablelist.listingDate}}</div>
        </li>

        <li>
          <div class="detail-li-label">tracking number</div>
          <div class="detail-li-value" :title="tablelist.courierNumber">{{tablelist.courierNumber}}</div>
        </li>
        <li>
          <div class="detail-li-label">corporate name</div>
          <div class="detail-li-value" :title="tablelist.company">{{tablelist.company}}</div>
        </li>
        <li>
          <div class="detail-li-label">telephone company</div>
          <div class="detail-li-value" :title="tablelist.tel">{{tablelist.tel}}</div>
        </li>

        <li>
          <div class="detail-li-label">responsible persons of companies</div>
          <div class="detail-li-value" :title="tablelist.companyPerson">{{tablelist.companyPerson}}</div>
        </li>
        <li>
          <div class="detail-li-label">the date of issuance</div>
          <div class="detail-li-value" :title="tablelist.deliveryDate">{{tablelist.deliveryDate}}</div>
        </li>
        <li>
          <div class="detail-li-label">region</div>
          <div class="detail-li-value" :title="tablelist.area">{{tablelist.area}}</div>
        </li>
        <li>
          <div class="detail-li-label">transaction status</div>
          <div class="detail-li-value" :title="tablelist.state">{{tablelist.state}}</div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import world from 'echarts/map/json/world';
import echarts from 'echarts';
import { geoCoordMap, planePath } from '../map';
import { findByCainId } from '@apis/user.js';
echarts.registerMap('world', JSON.stringify(world));

export default {
  props: {
    XAData: {
      type: Object,
      default: () => {}
    },
    list: {
      type: Array,
      default: () => []
    },
    isId: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      id: '',
      chartIntance: null,
      series: [],
      i: 1,
      tablelist: {
        traceabilityId: '',
        name: '',
        manufactureDate: '',
        manufactor: '',
        person: '',
        telephone: '',
        validityDate: '',
        batchNo: '',
        listingDate: '',
        courierNumber: '',
        company: '',
        tel: '',
        companyPerson: '',
        deliveryDate: '',
        area: '',
        state: ''
      } // 表格数据
    };
  },
  watch: {},
  mounted() {
    console.log(this.id, this.isId, '------------------');
    this.getList({ id: this.isId });
    this.init(this.XAData);
  },
  methods: {
    searchId() {
      this.$emit('test', this.id);
    },
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
            op: dataItem[1].op,
            id: dataItem[1].id
          });
        }
      }
      return res;
    },
    init(obj) {
      // 路线拼接逻辑
      Object.keys(obj).map(key => {
        this.setOptions(obj[key], key);
      });
      // 绘制地图
      this.drawMap();
    },
    // 配置路径参数 路线拼接逻辑
    setOptions(lineArr, key) {
      let that = this;
      let flag = key === 'chooseLine';
      let zsId = ['001', '003', '008'];
      let idFlag = zsId.includes(this.isId);
      that.series.push(
        {
          //红色线
          type: 'lines',
          zlevel: 1, //zlevel 大的 Canvas 会放在 zlevel 小的 Canvas 的上面
          effect: {
            show: true,
            period: 6,
            trailLength: 0.7,
            color: flag ? (idFlag ? 'red' : '#673ab7') : '#FF9800', //arrow箭头的颜色
            symbolSize: flag ? 5 : 3
          },
          lineStyle: {
            normal: {
              color: '#ffffff',
              width: 0,
              curveness: 0.2
            }
          },
          data: that.convertData(lineArr)
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
              color: '#ffffff', // planePath
              width: 1,
              opacity: 0.6,
              curveness: 0.2 //边的曲度，支持从 0 到 1 的值，值越大曲度越大。
            }
          },
          data: that.convertData(lineArr)
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
              color: '#7cdaf7'
            },
            emphasis: {
              areaColor: '#2B91B7'
            }
          },
          data: lineArr.map(function(dataItem) {
            return {
              name: dataItem[1].name,
              // 举例格式：{"name":"北京","value":[116.4551,40.2539,100]}
              value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value]) //格式是传给formatter的
            };
          })
        }
      );
    },
    screenAdapter() {
      this.chartIntance.resize();
    },
    drawMap(dataList) {
      console.log(dataList);
      // 基于准备好的dom，初始化echarts实例
      this.chartIntance = echarts.init(this.$refs.chinaMap);
      this.chartIntance.clear();
      let option = {
        tooltip: {
          trigger: 'item',
          formatter: function(params) {
            console.log(params, '--------------------------------------');
            if (params.seriesType == 'effectScatter') {
              console.log(JSON.stringify(params));
              return 'route：' + params.data.name + '' + params.data.value[2];
            } else if (params.seriesType == 'lines') {
              return (
                params.data.fromName +
                '--->' +
                params.data.toName +
                '<br/>' +
                `Date of manufacture：${params.data.op.a1}` +
                '<br/>' +
                `Food name：${params.data.op.a2}` +
                '<br/>' +
                `Departure：${params.data.op.a3}` +
                '<br/>' +
                `Manufacturer：${params.data.op.a4}` +
                '<br/>' +
                `Manufacturer's phone number：${params.data.op.a5}` +
                '<br/>' +
                `quality guarantee period：${params.data.op.a6}`
                // '<br />' +
                // `当前位置：东经${params.data.coords[0][0]},北纬${
                //   params.data.coords[0][1]
                // }
              );
            } else {
              return params.name;
            }
          }
        },
        geo: {
          map: 'world',
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
      this.chartIntance.setOption(option, true); // 绘制图表
      // 路线点击事件
      this.chartIntance.on('click', params => {
        if (params.componentSubType !== 'lines') return; // 点击线条才生效
        console.log(params);
        let data = params.data; // 获取线条数据，取到id 更新左下角表格数据
        //todo  更新左下侧表格数据
        this.getList({ id: data.id });
      });
    },

    // 获取表格数据

    getList(params) {
      // todo
      findByCainId(params)
        .then(res => {
          // this.tablelist = res;
          Object.keys(this.tablelist).map(key => {
            this.tablelist[key] = res[key]; // 赋值
          });
        })
        .catch(() => {});
    }
  },
  destroyed() {}
};
</script>

<style  lang="less" scoped>
.title {
  width: 100%;
  display: flex;
  justify-content: center;
}
.pie-title {
  background: url('../../../assets/info/map-title.png') no-repeat;
  background-size: 100% 100%;
  height: 30px;
  width: 50%;
  position: absolute;
  top: 2%;
}
.pie-content {
  height: 100%;
  position: absolute;
  width: 100%;
  overflow: hidden;
  display: flex;
}
.pie-content .com-chart {
  height: 100%;
  width: 100%;
}

.detail {
  position: absolute;
  left: 30px;
  z-index: 100000;
  width: 350px;
  height: 400px;
  bottom: 50px;
  background: url('../../../assets/info/info.png') no-repeat;
  background-size: 100% 100%;
  position: absolute;
  color: #fff;
  &-title {
    text-align: center;
    width: 100%;
    line-height: 50px;
  }
  &-line {
    background: url('../../../assets/info/t-line.png') no-repeat;
    background-size: 100% 100%;
    height: 2px;
    width: 100%;
  }
  ul {
    height: 340px;
    margin: 0 10px;
    overflow-y: scroll;
    font-size: 12px;
  }
  li {
    list-style-type: none;
    border-bottom: 1px solid #195cca;
    line-height: 50px;
    display: flex;
  }
  &-li-label {
    flex: 1;
    text-align: right;
  }
  &-li-value {
    flex: 1;
    // background: red;
    padding: 0 30px;
    overflow: hidden;
    text-overflow: emphasis;
    white-space: nowrap;
  }
}
.input {
  position: absolute;
  right: 60px;
  top: 60px;
  z-index: 10000;
  /deep/ .el-input__inner {
    // height: 50px;
    // border-radius: 10px;
    background: #003dab;
    border-color: #0078ff;
    color: #91b2f4;
    font-size: 14px;
    margin-top: 0;
    margin-left: 0;
    -webkit-box-shadow: 0 0 0 1000px #003dab inset;
    &::placeholder {
      color: #91b2f4;
    }
  }
}
/deep/ .el-input-group__append,
/deep/ .el-input-group__prepend {
  border: 1px solid #397ef6;
}
</style>
