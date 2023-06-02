<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "Plan",
    data(){
        return{
            options: [{
                value: '专业必修',
                label: '专业必修'
            }, {
                value: '选项2',
                label: '必修课'
            }, {
                value: '选项3',
                label: '选修课'
            }, {
                value: '',
                label: '请选择课程性质'
            }],
        }
    }
})
</script>

<template>
    <div style="overflow-x: hidden">
        <!--          搜索框-->
        <div style="margin: 10px 0">
            <el-input
                placeholder="请输入课程名称"
                prefix-icon="el-icon-search"
                v-model="course_name"
                style="width: 200px"
                class="ml-5">
            </el-input>
            <el-select v-model="type" placeholder="请选择课程性质" style="width: 200px" class="ml-5">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
            </el-select>
            <el-input
                placeholder="请输入课程学分"
                prefix-icon="el-icon-search"
                v-model="credit"
                style="width: 200px"
                class="ml-5">
            </el-input>
            <!--          搜索按钮-->
            <el-button type="primary" icon="el-icon-search" class="ml-5" @click="search">搜索</el-button>
        </div>
        <!--        数据表格-->
        <el-table :data="tableData"  stripe class="ml-5" height="750">
            <el-table-column property="course_name" label="课程名称"></el-table-column>
            <el-table-column property="type" label="课程性质"></el-table-column>
            <el-table-column property="credit" label="学分"></el-table-column>
            <el-table-column property="total_hours" label="总学时"></el-table-column>
            <el-table-column property="teaching_hours" label="授课学时"></el-table-column>
            <el-table-column property="experiment_hours" label="实验/上机学时"></el-table-column>
        </el-table>
    </div>
</template>

<style scoped>

</style>