<!--
 * @Author: yanchenxi
 * @Date: 2022-08-01 17:23:33
 * @LastEditTime: 2022-08-05 13:47:52
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \frontend\src\components\SelectTree.vue
-->
<template>
  <div class="warp">
    <div class="left">
      <div v-for="(item,index) in leftList" :key="`${index}-${item.label}`">
        <bfe-checkbox v-model="item.checked" style="margin-right:10px" />
        <span>{{item.label}}</span>
      </div>
      <div class="tip" v-if="leftList.length<=0">暂无数据</div>
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
        :render-content="renderContent"
      ></bfe-tree>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    arr: {
      type: Array,
      default: () => []
    },
    rightArr: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      leftList: this.arr,
      rightList: this.rightArr,
      defaultProps: {
        children: 'children',
        label: 'label',
        value: 'value'
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
      this.leftList = this.leftList.filter(v => !v.checked);
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
    },

    renderContent(h, { node }) {
      return (
        <span>
          <span>
            <span>{node.label}</span>

            <div>
              {node.level === 1 ? (
                ''
              ) : (
                <bfe-input
                  style="width:300px;margin-left:20px"
                  type="textarea"
                  value={node.data.value}
                />
              )}
            </div>
          </span>
        </span>
      );
    }
  }
};
</script>

<style lang="less" scoped>
.warp {
  width: 67%;
  display: flex;
  justify-content: space-between;
  padding-left: 50px;
  .left {
    width: 45%;
    border: 1px solid #999;
    min-height: 300px;
    padding: 10px;
    div {
      margin-bottom: 5px;
    }
    .tip {
      text-align: center;
      margin: 20px;
      line-height: 300px;
      color: #777;
    }
  }
  .middle {
    width: 10%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .right {
    width: 45%;
    .bfe-tree {
      width: 100%;
      height: 400px;
      overflow-y: scroll;
    }
  }
}
</style>