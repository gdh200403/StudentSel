<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "info",
    data() {
        return {
            tableData: [{
                term:'sp22',
                course_id:'MATH1001.01',
                course_name:'数学分析(B2)',
                credit:6,
                teacher_name:'张三',
                grade: 90,
            }, {
                term:'sp22',
                course_id:'MATH1001.01',
                course_name:'数学分析(B2)',
                credit:6,
                teacher_name:'张三',
                grade: 90,
            }, {
                term:'sp22',
                course_id:'MATH1001.01',
                course_name:'数学分析(B2)',
                credit:6,
                teacher_name:'张三',
                grade: 90,
            }, {
                term:'sp22',
                course_id:'MATH1001.01',
                course_name:'数学分析(B2)',
                credit:6,
                teacher_name:'张三',
                grade: 90,
            }],
            options: [
            //     {
            //     value: 'sp23',
            //     label: 'sp23'
            // },
            //     认为sp23是选课学期 不开放成绩查询
                {
                value: 'sp22',
                label: 'sp22'
            }, {
                value: 'sp21',
                label: 'sp21'
            }],
            search: '',
            total_credit: 40,
            credit_passed:38,
            credit_failed:2,
            arith_ave:89.23,
            weighted_ave:90.86,
        }
    },
    methods: {
        handleEdit(index, row) {
            console.log(index, row);
        },
        handleDelete(index, row) {
            console.log(index, row);
        }
    },
})
</script>

<template>
    <div>
        <el-descriptions class="info" title="个人信息" :column="3" border>
            <template slot="extra">
                <el-button type="primary" size="small">编辑</el-button>
            </template>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    学号
                </template>
                18100000000
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    姓名
                </template>
                kooriookami
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    性别
                </template>
                男
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    年龄
                </template>
                18
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    专业
                </template>
                计算机科学与技术
            </el-descriptions-item>
        </el-descriptions>
        <el-descriptions class="info" title="成绩信息" style="margin-top: 50px"></el-descriptions>
        <el-table
            :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
            class="info"
            stripe>
            <el-table-column
                label="授课学期"
                prop="term">
            </el-table-column>
            <el-table-column
                label="课程ID"
                prop="course_id">
            </el-table-column>
            <el-table-column
                label="课程名称"
                prop="course_name">
            </el-table-column>
            <el-table-column
                label="学分"
                prop="credit">
            </el-table-column>
            <el-table-column
                label="授课教师"
                prop="teacher_name">
            </el-table-column>
            <el-table-column
                label="课程成绩"
                prop="grade">
            </el-table-column>
            <el-table-column
                align="right">
                <template slot="header" slot-scope="scope">
                    <el-select v-model="tableData.type" placeholder="请选择授课学期">
                        <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                        </el-option>
                    </el-select>
                </template>
            </el-table-column>
        </el-table>
        <div style="width: 100vh;margin: 0 auto">
        <el-row gutter="20" style="margin-top: 30px">
                <el-col span="4">
                    <el-statistic precision="1" title="总学分" :value="total_credit"></el-statistic>
                </el-col>
                <el-col span="4">
                    <el-statistic precision="1" title="已获学分" :value="credit_passed"></el-statistic>
                </el-col>
                <el-col span="4">
                    <el-statistic precision="1" title="不及格学分" :value="credit_failed"></el-statistic>
                </el-col>
                <el-col span="4">
                    <el-statistic precision="2" title="算术平均分" :value="arith_ave"></el-statistic>
                </el-col>
                <el-col span="4">
                    <el-statistic precision="2" title="算术平均分" :value="weighted_ave"></el-statistic>
                </el-col>
        </el-row>
        </div>
    </div>
</template>

<style scoped>
.info{
    width: 90%;
    display: block;
    margin: auto;
}
</style>