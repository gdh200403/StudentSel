<script>
import StudentHeader from "@/components/Student/StudentHeader.vue";
import StudentAside from "@/components/Student/StudentAside.vue";
export default {
    name: "Admin",
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
        StudentAside,
        StudentHeader,
    },
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

<template>
    <el-container>
        <!--    左侧边栏导航菜单-->
        <el-aside :width="sideWidth + 'px'">
            <StudentAside :isCollapse="isCollapse"/>
        </el-aside>
        <el-container>
            <!--      顶栏-->
            <el-header>
                <StudentHeader :collapseBtnClass="collapseBtnClass" :collapse="collapse"/>
            </el-header>
            <!--      主体内容-->
            <el-main>
                <router-view/>
            </el-main>
        </el-container>
    </el-container>
</template>

<style scoped>
.el-header {
    background-color: #ffffff;
    color: #333;
    line-height: 60px;
    border-bottom: 1px solid #ccc;
}
.el-aside{
    box-shadow: 2px 0 6px rgb(0 21 41 / 35%);
}
.el-container{
    min-height: 100vh
}
.StudentAside{
    padding-bottom: 20px
}
.el-main{
    overflow-x: hidden
}
</style>