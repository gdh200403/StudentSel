<script>
export default {
    name: "TeacherHeader",
    props: {
        collapseBtnClass: String,
        collapse: Function,
    },
    data() {
        return {
            user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {}
        }
    },
    methods: {
        logout() {
            this.$confirm('确定退出吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                localStorage.removeItem('user')
                this.$router.push({path: '/login'})
            }).catch(() => {
            });
        }
        // collapse() {
        //     this.collapse()
        // }
    }
}
</script>

<template>
    <div style="line-height: 60px; display: flex">
        <div style="flex: 1;font-size: 18px">
            <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="text-align:left;cursor: pointer" trigger="click">
            <div>
                <span>{{user.username}}</span>
                <i class="el-icon-arrow-down"></i>
            </div>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item><i class="el-icon-user"></i>个人信息</el-dropdown-item>
                <el-dropdown-item >
                    <div @click="logout">
                        <i class="el-icon-switch-button"  ></i>退出登陆
                    </div>
                </el-dropdown-item>
                <!--              <el-dropdown-item style="font-size: 14px; padding: 5px 0">-->
                <!--                  <span style="text-decoration: none" @click="logout">退出</span>-->
                <!--              </el-dropdown-item>-->
            </el-dropdown-menu>
        </el-dropdown>
    </div>
</template>

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