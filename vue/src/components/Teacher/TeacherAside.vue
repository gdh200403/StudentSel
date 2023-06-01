<template>
    <el-menu background-color="rgb(48, 65, 86)"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
             router
    >
        <div class="menu-header">
            <img src="../../assets/logo.png" class="logo" alt="el-icon-picture-outline">
            <b style="color: white" v-show="!isCollapse">选课平台教师端</b>
        </div>
        <el-menu-item index="/teacher/courseMan">
          <i class="el-icon-user-solid"></i>
          <span v-show="!isCollapse">课程管理</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: "TeacherAside",
    props: {
        isCollapse: Boolean,
    },
}
</script>

<style>
.logo {
    width: 18px;
    position:relative;
    top: 3px;
    margin-right: 5px;
}
.menu-header{
    height: 60px;
    line-height: 60px;
    text-align: center;
}
.el-menu {
  min-height: 100vh;
  overflow-x: hidden;
  border-right-color: transparent;
}
/*.el-menu-item.is-active {*/
/*    background-color: rgb(38, 52, 69) !important;*/
/*}*/
/*.el-menu-item:hover {*/
/*    background-color: rgb(38, 52, 69) !important;*/
/*}*/

/*.el-submenu__title:hover {*/
/*    background-color: rgb(38, 52, 69) !important;*/
/*}*/
/*!*解决收缩菜单文字不消失问题*!*/
/*.el-menu--collapse span {*/
/*    visibility: hidden;*/
/*}*/
</style>