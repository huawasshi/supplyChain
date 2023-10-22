<!--
 * @Author: yanchenxi
 * @Date: 2021-11-11 10:19:13
 * @LastEditTime: 2023-10-21 15:07:14
 * @LastEditors: yanchenxi
 * @Description:
 * @FilePath: \world-demo\src\views\map\Index.vue
-->
<template>
  <div class="warp">
    <section class="header">
      <div class="content-middle box">
        <div class="content-middle-bottom">
          <china-map :key="i" @test="test" :XAData="XAData" :isId="isId" />
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import ChinaMap from './components/ChinaMap';

const chooseLine = [];
const otherLine = [
  [
    {
      name: 'United States - Washington'
    },
    {
      name: 'Russian-Moscow',
      id: '006',
      value: 100,
      op: {
        a1: '32122235',
        a2: '546ms',
        a3: '2022-07-04',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Hefei'
    },
    {
      name: 'Malaysia',
      value: 100,
      id: '001',
      op: {
        a1: '12312321',
        a2: '38.7ms',
        a3: '2022-07-01',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Hefei'
    },
    {
      name: 'Melbourne',
      value: 100,
      id: '002',
      op: {
        a1: '12312321',
        a2: '38.7ms',
        a3: '2022-07-01',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Hefei'
    },
    {
      name: 'United States - Washington',
      id: '003',
      value: 100,
      op: {
        a1: '12312321',
        a2: '38.7ms',
        a3: '2022-07-01',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'XiAn'
    },
    {
      name: 'Angola-luanda',
      id: '004',
      value: 100,
      op: {
        a1: '12311235',
        a2: '56ms',
        a3: '2022-07-02',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Hefei'
    },
    {
      name: 'Japan-Tokyo',
      id: '005',
      value: 100,
      op: {
        a1: '12322235',
        a2: '546ms',
        a3: '2022-07-03',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Hefei'
    },
    {
      name: 'WuLuMuQi',
      id: '007',
      value: 100,
      op: {
        a1: '32122235',
        a2: '546ms',
        a3: '2022-07-04',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Hefei'
    },
    {
      name: 'Spain - Madrid',
      id: '008',
      value: 100,
      op: {
        a1: '34222235',
        a2: '546ms',
        a3: '2022-07-05',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Lasa'
    },
    {
      name: 'Canada-Vancouver',
      id: '009',
      value: 100,
      op: {
        a1: '66312321',
        a2: '546ms',
        a3: '2022-07-06',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Canada-Vancouver'
    },
    {
      name: 'Russian-Moscow',
      id: '010',
      value: 100,
      op: {
        a1: '66312390',
        a2: '546ms',
        a3: '2022-07-07',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Shenzhen'
    },
    {
      name: 'Beijin',
      id: '011',
      value: 100,
      op: {
        a1: '66312321',
        a2: '46ms',
        a3: '2022-07-08',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ],
  [
    {
      name: 'Shenzhen'
    },
    {
      name: 'WuLuMuQi',
      id: '012',
      value: 100,
      op: {
        a1: '77312321',
        a2: '1546ms',
        a3: '2022-07-09',
        a4: '2℃',
        a5: '',
        a6: ''
      }
    }
  ]
];
export default {
  name: '',
  components: {
    ChinaMap
  },
  data() {
    return {
      time: '',
      days: '',
      times: '',
      getDataTimer: null,
      i: 1,
      isId: '',
      XAData: {
        chooseLine,
        otherLine
      }
    };
  },
  methods: {
    test(id) {
      let arr = this.XAData;
      this.isId = id;
      if (!otherLine.some(item => item[1].id === id)) return;
      arr.chooseLine = [otherLine.find(item => item[1].id === id)];
      arr.otherLine = otherLine.filter(item => item[1].id !== id);
      this.XAData = arr;
      this.i++; // 更新虚拟dom的key值，重新渲染
    },
    goBack() {}
  },
  mounted() {},
  destroyed() {}
};
</script>

<style scoped >
.content-middle-bottom {
  background-image: url('../../assets/dashborad/bg.png');
  background-repeat: no-repeat;
  background-size: 100% 100%;
  height: 100vh;
  /* margin-top: 2vh; */
  width: 100%;
}
</style>
