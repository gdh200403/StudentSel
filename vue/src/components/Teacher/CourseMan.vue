<template>
    <div style="overflow-x: hidden">
        <!--          搜索框-->
        <div style="margin: 10px 0">
            <el-input
                v-model="course_id"
                class="ml-5"
                placeholder="请输入课程ID"
                prefix-icon="el-icon-search"
                style="width: 200px;">
            </el-input>
            <el-input
                v-model="course_name"
                class="ml-5"
                placeholder="请输入课程名称"
                prefix-icon="el-icon-search"
                style="width: 200px">
            </el-input>
            <el-input
                v-model="credit"
                class="ml-5"
                placeholder="请输入课程学分"
                prefix-icon="el-icon-search"
                style="width: 200px">
            </el-input>
            <el-input
                v-model="place"
                class="ml-5"
                placeholder="请输入授课地点"
                prefix-icon="el-icon-search"
                style="width: 200px">
            </el-input>
            <!--          搜索按钮-->
            <el-button class="ml-5" icon="el-icon-search" type="primary" @click="search">搜索</el-button>
        </div>
        <!--        新增按钮-->
        <div style="margin: 10px 0">
            <el-button class="ml-5" type="primary" @click="handle_add">新增<i class="el-icon-circle-plus-outline"></i>
            </el-button>
        </div>
        <!--        数据表格-->
        <el-table :data="tableData" class="ml-5" height="700" stripe style="width: 100%">
            <el-table-column label="课程ID" property="course_id" width="100"></el-table-column>
            <el-table-column label="课程名称" property="course_name" width="150"></el-table-column>
            <el-table-column label="授课学期" property="term" width="100"></el-table-column>
            <el-table-column label="课程性质" property="type" width="100"></el-table-column>
            <el-table-column label="授课教师ID" property="teacher_id" width="100"></el-table-column>
            <el-table-column label="授课教师" property="teacher_name" width="100"></el-table-column>
            <el-table-column label="授课地点" property="place" width="100"></el-table-column>
            <el-table-column label="学分" property="credit" width="80"></el-table-column>
            <el-table-column label="总学时" property="total_hours" width="80"></el-table-column>
            <el-table-column label="授课学时" property="teaching_hours" width="80"></el-table-column>
            <el-table-column label="实验/上机学时" property="experiment_hours" width="80"></el-table-column>
            <el-table-column label="已选人数" property="current" width="80"></el-table-column>
            <el-table-column label="选课上限" property="capacity" width="80"></el-table-column>
            <el-table-column label="课程备注" property="comment"></el-table-column>
            <!--          点开查看学生-->
            <el-table-column align="right">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="success"
                        @click="handleEdit(scope.row)">
                        <i class="el-icon-edit-outline"></i>编辑
                    </el-button>
                    <el-button
                        size="mini"
                        style="width: 90px"
                        type="success"
                        @click="handleGrade(scope.row.course_id)">
                        <i class="el-icon-edit-outline"></i>评分
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="padding: 10px;text-align: center">
            <!--          分页控制-->
            <el-pagination
                :current-page="currentPage"
                :page-size="pageSize"
                :page-sizes="[2, 5, 10, 15, 20]"
                :total="total"
                layout="total, sizes, prev, pager, next, jumper"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange">
            </el-pagination>
        </div>
        <!--        编辑对话框-->
        <el-dialog
            :visible.sync="dialogFormVisible"
            title="授课信息"
            width="30%">
            <el-form class="demo-ruleForm" label-width="100px">
                <el-form-item label="课程ID" prop="course_id">
                    <el-input v-model="form.course_id" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="课程名称" prop="course_name">
                    <el-input v-model="form.course_name" disabled></el-input>
                </el-form-item>
                <el-form-item label="教师ID" prop="course_id">
                    <el-input v-model="form.teacher_id" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="教师姓名" prop="course_name">
                    <el-input v-model="form.teacher_name" disabled></el-input>
                </el-form-item>
                <el-form-item label="授课学期" prop="comment">
                    <el-input v-model="form.term"></el-input>
                </el-form-item>
                <el-form-item label="授课地点" prop="place">
                    <el-input v-model="form.place"></el-input>
                </el-form-item>
                <el-form-item label="已选人数" prop="current">
                    <el-input v-model="form.current"></el-input>
                </el-form-item>
                <el-form-item label="选课上限" prop="capacity">
                    <el-input v-model="form.capacity"></el-input>
                </el-form-item>
                <el-form-item label="课程备注" prop="comment">
                    <el-input v-model="form.comment"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="save">确 定</el-button>
            </div>
        </el-dialog>
        <!--        编辑对话框-->
        <el-dialog
            :visible.sync="innerdialogFormVisible"
            title="評分信息"
            width="60%">
            <div style="margin: 10px 0">
                <el-input
                    v-model="student_id"
                    class="ml-5"
                    placeholder="请输入学号"
                    prefix-icon="el-icon-search"
                    style="width: 200px;">
                </el-input>
                <el-input
                    v-model="student_name"
                    class="ml-5"
                    placeholder="请输入姓名"
                    prefix-icon="el-icon-search"
                    style="width: 200px">
                </el-input>
                <!--          搜索按钮-->
                <el-button class="ml-5" icon="el-icon-search" type="primary" @click="innerSearch">搜索</el-button>
            </div>
            <el-table :data="innerData" class="ml-5" height="700" stripe style="width: 100%">
                <el-table-column label="学号" property="student_id" width="180"></el-table-column>
                <el-table-column label="姓名" property="student_name" width="120"></el-table-column>
                <el-table-column label="课程ID" property="course_id" width="100"></el-table-column>
                <el-table-column label="课程名称" property="course_name" width="150"></el-table-column>
                <el-table-column label="成绩" property="grade" width="100"></el-table-column>
                <!--          编辑与删除-->
                <el-table-column align="right">
                    <template slot-scope="scope">
                        <el-button
                            size="mini"
                            type="success"
                            @click="handleGiveScore(scope.row)">
                            <i class="el-icon-edit-outline"></i>評分
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div style="padding: 10px;text-align: center">
                <!--          分页控制-->
                <el-pagination
                    :current-page="innerCurrentPageStudent"
                    :page-size="innerPageSizeStudent"
                    :page-sizes="[2, 5, 10, 15, 20]"
                    :total="innerTotal"
                    layout="total, sizes, prev, pager, next, jumper"
                    @size-change="handleSizeChangeStudent"
                    @current-change="handleCurrentChangeStudent">
                </el-pagination>
            </div>
        </el-dialog>
        <el-dialog title="评分信息" :visible.sync="gradeDialogFormVisible" width="30%">
            <el-form label-width="100px">
                <el-form-item label="学生学号">
                    <el-input :disabled = "true" v-model="grade.student_id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="学生姓名">
                    <el-input :disabled = "true" v-model="grade.student_name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="成绩">
                    <el-input v-model="grade.grade" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirm">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>

export default {
    name: "Teacher",
    computed: {},
    data() {
        const item = {};
        return {
            user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {},
            teacher_id: '',
            teacher_name: '',
            course_id: '',
            course_name: '',
            type: '',
            place: '',
            credit: '',
            form: {
                teacher_id: '',
                teacher_name: '',
                course_id: '',
                course_name: '',
                current: '',
                capacity: '',
                term: '',
                place: '',
                comment: '',
            },
            student_id: '',
            student_name: '',
            grade: {
                student_id: '',
                student_name: '',
                course_id: '',
                course_name: '',
                term: '',
                grade: '',
            },
            dialogFormVisible: false,
            disableEdit: false,
            tableData: Array(0).fill(item),
            innerData: Array(0).fill(item),
            total: 10,
            currentPage: 1,
            pageSize: 5,
            innerCurrentPageStudent: 1,
            innerPageSizeStudent: 5,
            innerTotal: 10,
            innerdialogFormVisible: false,
            gradeDialogFormVisible: false,
        }
    },
    created() {
        this.load()
    },
    // if search

    methods: {
        load() {
            this.request.get('/api/teacher/page/tc', {
                    params: {
                        teacher_id: this.user.username,
                        teacher_name: "",
                        course_id: (this.course_id === '' ? "" : this.course_id),
                        course_name: (this.course_name === '' ? "" : this.course_name),
                        type: (this.type === '' ? "" : this.type),
                        place: (this.place === '' ? "" : this.place),
                        credit: (this.credit === '' ? -1 : this.credit),
                        page: this.currentPage,
                        pageSize: this.pageSize
                    }
                }
            )
                .then(res => {
                    if (res.status === 200) {
                        this.tableData = res.data.list
                        this.total = res.data.total
                    } else {
                        this.$message.error(res.msg)
                    }
                })

        },
        handleEdit(row) {
            this.dialogFormVisible = true
            this.form = Object.assign({}, row)
        },
        handleGrade(course_id) {
            this.grade.course_id = course_id
            this.innerload()
            this.innerdialogFormVisible = true
        },
        innerload() {
            this.request.get('/api/teacher/page/sc', {
                    params: {
                        student_id: (this.student_id === '' ? "" : this.student_id),
                        student_name: (this.student_name === '' ? "" : this.student_name),
                        teacher_id: this.user.username,
                        teacher_name: "",
                        course_id: this.grade.course_id,
                        course_name: "",
                        type: "",
                        place: "",
                        credit: -1,
                        page: this.innerCurrentPageStudent,
                        pageSize: this.innerPageSizeStudent,
                    }
                }
            )
                .then(res => {
                    if (res.status === 200) {
                        this.innerData = res.data.list
                        this.innerTotal = res.data.total
                    } else {
                        this.$message.error(res.msg)
                    }
                },)
        },
        handleGiveScore(row) {
            this.grade.student_id = row.student_id
            this.grade.student_name = row.student_name
            this.grade.course_id = row.course_id
            this.grade.course_name = row.course_name
            this.grade.grade = row.grade
            this.gradeDialogFormVisible = true

        },
        confirm() {
            this.request.post('/api/teacher/sc/update', this.grade)
                .then(res => {
                    if (res.status === 200) {
                        this.$message.success(res.msg)
                        this.gradeDialogFormVisible = false
                        this.innerload()
                    } else {
                        this.$message.error(res.msg)
                    }
                })
        },
        save() {
            this.request.post('/api/teacher/tc/update', this.form)
                .then(res => {
                    if (res.status === 200) {
                        this.$message.success(res.msg)
                        this.dialogFormVisible = false
                        this.load()
                    } else {
                        this.$message.error(res.msg)
                    }
                })
        },
        search() {
            this.load()
        },
        innerSearch() {
            this.innerload()
        },
        handleSizeChange(pageSize) {
            this.pageSize = pageSize
            this.load()
        },
        handleCurrentChange(currentPage) {
            this.currentPage = currentPage
            this.load()
        },
        handleSizeChangeStudent(pageSize) {
            this.innerPageSizeStudent = pageSize
            this.innerload()
        },
        handleCurrentChangeStudent(currentPage) {
            this.innerCurrentPageStudent = currentPage
            this.innerload()
        },
    }
}
</script>

<style scoped>
.el-header {
    background-color: #ffffff;
    color: #333;
    line-height: 60px;
}

.el-menu {
    min-height: 100vh;
    overflow-x: hidden;
    border-right-color: transparent;
}

.menu-header {
    height: 60px;
    line-height: 60px;
    text-align: center;
}

.logo {
    width: 18px;
    position: relative;
    top: 3px;
    margin-right: 5px;
}
</style>