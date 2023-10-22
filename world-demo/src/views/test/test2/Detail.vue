<!--
 * @Author: yanchenxi
 * @Date: 2022-06-08 19:21:39
 * @LastEditTime: 2022-07-24 13:42:57
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\views\test\test2\Detail.vue
-->
<template>
  <div class="warp">
    <div class="left">
      <div v-for="(item,index) in leftList" :key="`${index}-${item.label}`">
        <bfe-checkbox v-model="item.checked" style="margin-right:10px" />
        <span>{{item.label}}</span>
      </div>
    </div>
    <div class="middle">
      <div>
        <div>
          <bfe-button @click="add" icon="arrow-right-a" type="primary"></bfe-button>
        </div>
        <div style="margin-top:20px">
          <bfe-button @click="remove" icon="arrow-left-a" type="primary"></bfe-button>
        </div>
      </div>
    </div>
    <div class="right">
      <bfe-tree
        :key="rekey"
        :data="rightList"
        show-checkbox
        node-key="id"
        ref="tree"
        @current-change="test"
        :props="defaultProps"
      ></bfe-tree>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      leftList: [
        {
          checked: false,
          label: '我的老家',
          id: 1,
          children: [
            {
              id: 4,
              label: '二级 1-1'
            }
          ]
        },
        {
          checked: false,
          label: '312',
          id: 2,
          children: [
            {
              id: 5,
              label: '二级 2-1'
            },
            {
              id: 6,
              label: '二级 2-2'
            }
          ]
        },
        {
          checked: true,
          label: 'qweqw',
          id: 3,
          children: [
            {
              id: 7,
              label: '二级 3-1'
            },
            {
              id: 8,
              label: '二级 3-2'
            }
          ]
        },
        { checked: false, label: 'ghjghj', id: 10 }
      ],
      rightList: [],

      defaultProps: {
        children: 'children',
        label: 'label'
      },
      rekey: 1
    };
  },
  methods: {
    test(data, node) {
      console.log(data, node);
    },
    add() {
      console.log(this.leftList);
      let list = this.leftList.filter(v => v.checked);
      let t = this.leftList.filter(v => !v.checked);
      this.leftList = t;
      this.rightList.push(...list);
      this.rightList = [...new Set(this.rightList)]; // 去重
    },
    remove() {
      let arr = this.$refs.tree.getCheckedKeys();
      this.moveLeft(arr);
    },
    moveLeft(arr) {
      arr.map(v => {
        this.rightList.map(item => {
          if (item.id === v) {
            this.moveItem(item);
            return;
          }
          if (item.children && item.children.length > 0) {
            item.children.map(t => {
              if (t.id === v) {
                this.moveItem(item);
                return;
              }
            });
          }
        });
      });
    },
    moveItem(item) {
      if (!this.hasOwn(item)) {
        this.leftList.push(item);
        this.rightList = this.rightList.filter(v => v.id !== item.id);
      }
    },
    hasOwn(item) {
      return this.leftList.some(e => e.id === item.id);
    }
  }
};
</script>

<style lang="less" scoped>
.warp {
  width: 600px;
  display: flex;
  justify-content: space-between;
  .left {
    width: 200px;
    border: 1px solid #999;
    min-height: 300px;
    padding: 10px;
    div {
      margin-bottom: 5px;
    }
  }
  .middle {
    width: 200px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
}
.bfe-tree {
  width: 200px;
  min-height: 300px;
}
</style>