<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "CourseAll",
    data(){
        return{
            user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {},
            teacher_id: '',
            teacher_name: '',
            course_id: '',
            course_name: '',
            term:'',
            currentTerm: 'sp23',
            type: '',
            place: '',
            credit: '',
            form : {
                student_id: '',
                teacher_id: '',
                course_id: '',
                term: '',
            },
            course:[{//TODO 显示数据
                teacher_id: '123',
                teacher_name: '123',
                course_id: '123',
                course_name: '123',
                type: '123',
                credit: 123,
                total_hours: 123,
                teaching_hours: 123,
                experiment_hours: 123,
                current: 90,
                capacity: 100,
                term: 'sp23',
                place: '123',
                time:'123',
                selected:false,
            }],
            options: [{
                value: '必修',
                label: '必修'
            }, {
                value: '选修',
                label: '选修'
            }, {
                value: '通识',
                label: '通识'
            }, {
                value: '',
                label: '无'
            }],
            credit_selected: 0,
            selectedCourses: [],
            errorDialogVisible: false,
            errorMessage: '',
            total : 10,
            currentPage: 1,
            pageSize: 5,
        }
    },
    created() {
        this.load()
    },
    methods:{
        load(){

            this.request.get('/api/student/page/tc', {
                    params: {
                        teacher_id: (this.teacher_id === '' ? "" : this.teacher_id),
                        teacher_name: (this.teacher_name === '' ? "" : this.teacher_name),
                        course_id: (this.course_id === '' ? "" : this.course_id),
                        course_name: (this.course_name === '' ? "" : this.course_name),
                        term: this.currentTerm,
                        type: (this.type === '' ? "" : this.type),
                        place: (this.place === '' ? "" : this.place),
                        credit: (this.credit === '' ? -1 : this.credit),
                        page: this.currentPage,
                        pageSize: this.pageSize
                    }
                }
            )
                .then( res => {
                    if (res.status === 200) {
                        this.course = res.data.list
                        this.total = res.data.total
                    } else {
                        this.$message.error(res.msg)
                    }
                })

            this.request.get('/api/student/current/credit', {
                params: {
                    student_id: this.user.username,
                    term: this.currentTerm,
                }
            }).then(res => {
                console.log(res)
                this.credit_selected = res.data

            }).catch(err => {
                console.log(err)
            })

        },
        search(){
            this.load()
        },
        selectCourse(row) {
            this.form.student_id = this.user.username
            this.form.course_id = row.course_id
            this.form.teacher_id = row.teacher_id
            this.form.term = row.term
            this.request.post('/api/student/sc/save', this.form)
                .then(res => {
                    if (res.status === 200) {
                        this.$message.success('选课成功')
                        this.load()
                    } else {
                        this.$message.error(res.msg)
                    }
                })
        },
        handleSizeChange(pageSize) {
            this.currentPage = 1
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
        <!--          搜索框-->
        <div style="margin: 10px 0">
            <el-input
                placeholder="请输入课程ID"
                prefix-icon="el-icon-search"
                v-model="course_id"
                style="width: 200px;"
                class="ml-5">
            </el-input>
            <el-input
                placeholder="请输入课程名称"
                prefix-icon="el-icon-search"
                v-model="course_name"
                style="width: 200px"
                class="ml-5">
            </el-input>
            <el-input
                placeholder="请输入授课学期"
                prefix-icon="el-icon-search"
                v-model="term"
                style="width: 200px"
                class="ml-5">
            </el-input>
            <el-input
                placeholder="请输入授课教师姓名"
                prefix-icon="el-icon-search"
                v-model="teacher_name"
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
            <div style=" justify-content: center;display:flex;margin-top: 12px">
                <el-tag>
                <h1>当前选择的总学分:{{credit_selected}}</h1>
                </el-tag>
            </div>
        </div>
        <el-table :data="course" style="width: 100%" height="72vh" >
            <el-table-column prop="course_id" label="课程ID"></el-table-column>
            <el-table-column prop="course_name" label="课程名称"></el-table-column>
            <el-table-column prop="term" label="授课学期"></el-table-column>
            <el-table-column prop="type" label="课程种类"></el-table-column>
            <el-table-column prop="teacher_id" label="授课教师ID"></el-table-column>
            <el-table-column prop="teacher_name" label="授课教师"></el-table-column>
            <el-table-column prop="credit" label="学分"></el-table-column>
            <el-table-column prop="total_hours" label="总学时"></el-table-column>
            <el-table-column prop="teaching_hours" label="授课学时"></el-table-column>
            <el-table-column prop="experiment_hours" label="实验学时"></el-table-column>
            <el-table-column prop="time" label="上课时间"></el-table-column>
            <el-table-column prop="place" label="上课地点"></el-table-column>
            <el-table-column prop="current" label="已选人数" fixed="right" width="70"></el-table-column>
            <el-table-column prop="capacity" label="选课上限" fixed="right" width="70"></el-table-column>
            <el-table-column prop="status" label="选课状态" fixed="right" width="100">
                <template slot-scope="{ row }">
                    <div style="display: flex; align-items: center;width: 70px">
                        <div style="flex: 1; ">
                            <el-progress :percentage="row.current / row.capacity * 100"
                                         :stroke-width="8"
                                         :show-text="false"
                            color="#a0cfff"></el-progress>
                        </div>
                    </div>
                </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right">
                <template slot-scope="{ row }">
                    <el-button v-if="!row.selected" type="primary" @click="selectCourse(row)" size="mini">选课</el-button>
                    <el-tag v-else>已选中</el-tag>
                </template>
            </el-table-column>
        </el-table>
        <!--          分页控制-->
        <div style="padding: 10px;text-align: center">
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
        <el-dialog title="错误提示" :visible.sync="errorDialogVisible">
            <span>{{ errorMessage }}</span>
        </el-dialog>
    </div>
</template>

<style scoped>

</style>