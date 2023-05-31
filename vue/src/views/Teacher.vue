<template>
    <el-container style="min-height: 100vh">
        <!--    左侧边栏导航菜单-->
        <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
            <TeacherAside :isCollapse="isCollapse" style="padding-bottom: 20px" />
        </el-aside>
        <el-container>
            <!--      顶栏-->
            <el-header>
                <TeacherHeader :collapseBtnClass="collapseBtnClass" :collapse="collapse"/>
            </el-header>
            <!--      主体内容-->
            <el-main style="overflow-x: hidden">
                <router-view/>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>

import TeacherAside from "@/components/Teacher/TeacherAside.vue";
import TeacherHeader from "@/components/Teacher/TeacherHeader.vue";
export default {
    name: "Teacher",
    data() {
        const item = {

        };
        return {

            collapseBtnClass: 'el-icon-s-fold',
            isCollapse: false,
            sideWidth: 200,
        }
    },


    components: {
        TeacherAside,
        TeacherHeader
    },

    // if search

    methods:{
        collapse(){ //点击收缩按钮触发
            this.isCollapse=!this.isCollapse
            if(this.isCollapse){
                this.collapseBtnClass = 'el-icon-s-unfold'
                this.sideWidth = 64
            }else{
                this.collapseBtnClass = 'el-icon-s-fold'
                this.sideWidth = 200
            }
        },

    }
}
</script>

<style scoped>
.el-header {
    background-color: #ffffff;
    color: #333;
    line-height: 60px;
    border-bottom: 1px solid #ccc;
}


</style>