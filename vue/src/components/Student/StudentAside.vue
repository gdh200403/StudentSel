<script>
export default {
    name: "AdminAside",
    props: {
        isCollapse: Boolean,
    },
}
</script>

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
            <b style="color: white" v-show="!isCollapse">学生选课平台</b>
        </div>
        <el-menu-item index="/student/info">
            <i class="el-icon-user-solid"></i>
            <span>个人信息</span>
        </el-menu-item>
        <el-menu-item index="/student/courseSelected">
            <i class="el-icon-s-management"></i>
            <span>已选课程</span>
        </el-menu-item>
        <el-menu-item index="/student/courseAll">
            <i class="el-icon-search"></i>
            <span>开课查询</span>
        </el-menu-item>
<!--        <el-menu-item index="/student/plan">-->
<!--            <i class="el-icon-document"></i>-->
<!--            <span>培养计划</span>-->
<!--        </el-menu-item>-->
    </el-menu>
</template>

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
  min-height: 100%;
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