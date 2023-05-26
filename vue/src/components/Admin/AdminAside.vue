<template>
    <el-menu :default-openeds="opens" style="min-height: 100%; overflow-x: hidden"
             background-color="rgb(48, 65, 86)"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
             router
    >
        <div class="menu-header">
            <img src="../../assets/logo.png" class="logo" >
            <b style="color: white" v-show="logoTextShow">学生选课管理后台</b>
        </div>
        <el-submenu index="1" style="width: 200px">
            <template slot="title">
                <i class="el-icon-user-solid"></i>
                <span>学生管理</span>
            </template>
            <el-menu-item index="admin/studentMan">学生信息管理</el-menu-item>
            <el-menu-item index="1-2">学生选课管理</el-menu-item>
        </el-submenu>
        <el-menu-item index="2"> <i class="el-icon-s-custom"></i>
            <span>教师管理</span>
        </el-menu-item>
        <el-menu-item index="3"> <i class="el-icon-s-management"></i>
            <span>课程管理</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: "AdminAside",
    props: {
        isCollapse: Boolean,
        logoTextShow: Boolean,
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