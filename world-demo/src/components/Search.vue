<!--
 * @Author: yanchenxi
 * @Date: 2022-05-09 20:57:51
 * @LastEditTime: 2022-08-17 14:21:16
 * @LastEditors: yanchenxi
 * @Description: 
 * @FilePath: \yanshi\src\components\Search.vue
-->
<template>
  <div>
    <div class="search">
      <div class="search-box">
        <div class="search-box-tip">
          <slot name="title">查询条件</slot>
        </div>
        <div>
          <slot name="btn"></slot>
        </div>
      </div>
      <div class="search-query">
        <slot name="query"></slot>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="less" scoped>
.search {
  .search-box {
    display: flex;
    justify-content: space-between;
    border-bottom: 1px solid #4672cf;
    margin: 0 20px;
    line-height: 60px;
    .search-box-tip {
      color: @color-ziti;
    }
  }
  .search-query {
    margin: 20px;
  }
}
</style>
