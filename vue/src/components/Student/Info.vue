<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "info",
    data() {
        return {
            user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {},
            student : {
                student_id: '',
                student_name: '',
                sex: '',
                age: '',
                major: '',
            },

            tableData: [],
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
            total : 10,
            currentPage: 1,
            pageSize: 5,
        }
    },
    created() {
        this.load()
    },
    methods: {
        load() {
            //request for student Personal info
            this.request.get('/api/student/personal', {
                params: {
                    student_id: this.user.username,
                }
            }).then(res => {
                console.log(res)
                this.student = res.data
            }).catch(err => {
                console.log(err)
            })
            // request for student course info
            this.request.get('/api/student/page/sc', {
                params: {
                    student_id: this.user.username,
                    page: this.currentPage,
                    pageSize: this.pageSize
                }
            }).then(res => {
                console.log(res)
                this.tableData = res.data.pageInfo.list
                this.total = res.data.pageInfo.total
                this.total_credit = res.data.total_credit
                this.credit_passed = res.data.credit_passed
                this.credit_failed = res.data.credit_failed
                this.arith_ave = res.data.arith_ave
                this.weighted_ave = res.data.weighted_ave
            }).catch(err => {
                console.log(err)
            })
        },
        handleEdit(index, row) {
            console.log(index, row);
        },
        handleDelete(index, row) {
            console.log(index, row);
        },
        handleSizeChange(pageSize) {
            this.pageSize = pageSize
            this.load()
        },
        handleCurrentChange(currentPage) {
            this.currentPage = currentPage
            this.load()
        },
    }
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
                {{student.student_id}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    姓名
                </template>
                {{student.student_name}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    性别
                </template>
                {{student.sex}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    年龄
                </template>
                {{student.age}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    专业
                </template>
                {{student.major}}
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
        <div style="padding: 10px;text-align: center">
            <!--          分页控制-->
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[2, 5, 10, 15, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </div>
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