<template>
  <div style="line-height: 60px; display: flex">
      <div style="flex: 1;font-size: 18px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
      </div>
      <el-dropdown style="text-align:left;cursor: pointer" trigger="click">
          <div>
              <span>王小虎</span>
              <i class="el-icon-arrow-down"></i>
          </div>
          <el-dropdown-menu slot="dropdown">
              <el-dropdown-item><i class="el-icon-user"></i>个人信息</el-dropdown-item>
              <el-dropdown-item><i class="el-icon-close"></i>退出登录</el-dropdown-item>
          </el-dropdown-menu>
      </el-dropdown>
  </div>
</template>

<script>
export default {
    name: "TeacherHeader",
    props: {
        collapseBtnClass: String,
        collapse: Function,
    },
    methods: {
        // collapse() {
        //     this.collapse()
        // }
    }
}
</script>

<style>
/*.el-header {*/
/*    background-color: #ffffff;*/
/*    color: #333;*/
/*    line-height: 60px;*/
/*}*/

/*.el-menu {*/
/*    min-height: 100vh;*/
/*    overflow-x: hidden;*/
/*    border-right-color: transparent;*/
/*}*/

/*.menu-header{*/
/*    height: 60px;*/
/*    line-height: 60px;*/
/*    text-align: center;*/
/*}*/

/*.logo {*/
/*    width: 18px;*/
/*    position:relative;*/
/*    top: 3px;*/
/*    margin-right: 5px;*/
/*}*/
</style>