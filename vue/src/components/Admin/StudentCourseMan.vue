<template>
    <div style="overflow-x: hidden">
        <!--          搜索框-->
        <div style="margin: 10px 0">
            <el-input
                placeholder="请输入学号"
                prefix-icon="el-icon-search"
                v-model="student_id"
                style="width: 200px;"
                class="ml-5">
            </el-input>
            <el-input
                placeholder="请输入姓名"
                prefix-icon="el-icon-search"
                v-model="student_name"
                style="width: 200px"
                class="ml-5">
            </el-input>
            <!--          搜索按钮-->
            <el-button type="primary" icon="el-icon-search" class="ml-5" @click="search">搜索</el-button>
        </div>
        <!--        数据表格-->
        <el-table :data="tableData"  stripe class="ml-5" height="700" style="width: 100%">
            <el-table-column property="student_id" label="学号" width="180"></el-table-column>
            <el-table-column property="student_name" label="姓名" width="120"></el-table-column>
            <el-table-column property="course_id" label="课程ID" width="100"></el-table-column>
            <el-table-column property="course_name" label="课程名称" width="150"></el-table-column>
            <el-table-column property="teacher_name" label="授课教师" width="100"></el-table-column>
            <el-table-column property="place" label="授课地点" width="100"></el-table-column>
            <el-table-column property="credit" label="学分" width="80"></el-table-column>
            <el-table-column property="total_hours" label="总学时" width="80"></el-table-column>
            <el-table-column property="current" label="已选人数" width="80"></el-table-column>
            <el-table-column property="capacity" label="选课上限" width="80"></el-table-column>
            <el-table-column property="comment" label="课程备注"></el-table-column>
            <!--          编辑与删除-->
            <el-table-column align="right">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="handle_add(scope.row.student_id, scope.row.student_name)"
                        type="success"
                    style="width: 90px">
                        <i class="el-icon-edit-outline"></i>新增选课
                    </el-button>
                    <el-popconfirm
                        class="ml-5"
                        confirm-button-text='确定'
                        cancel-button-text='取消'
                        icon="el-icon-info"
                        icon-color="red"
                        title="确定删除该学生的该条选课记录吗？"
                        @confirm="handleDelete(scope.row)"
                    >
                        <el-button
                            size="mini"
                            type="danger"
                            slot = "reference"
                        style="width: 90px;margin-top: 5px"
                        >
                            <i class="el-icon-delete"></i>删除
                        </el-button>
                    </el-popconfirm>
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
        <!--        新增选课对话框-->
        <el-dialog
            title="学生选课"
            :visible.sync="dialogFormVisible"
            width="80%">
            <el-input
                placeholder="请输入课程ID"
                prefix-icon="el-icon-search"
                v-model="inner_course_id"
                style="width: 200px;"
                class="ml-5">
            </el-input>
            <el-input
                placeholder="请输入课程名称"
                prefix-icon="el-icon-search"
                v-model="inner_course_name"
                style="width: 200px"
                class="ml-5">
            </el-input>
            <el-button type="primary" icon="el-icon-search" class="ml-5" @click="search_course">搜索</el-button>
                <!--        数据表格-->
                <el-table :data="innerTableData"  stripe class="ml-5" height="500" style="width: 100%">
                    <el-table-column property="course_id" label="课程ID" width="100"></el-table-column>
                    <el-table-column property="course_name" label="课程名称" width="150"></el-table-column>
                    <el-table-column property="term" label="授课学期" width="100"></el-table-column>
                    <el-table-column property="type" label="课程性质" width="100"></el-table-column>
                    <el-table-column property="teacher_id" label="授课教师ID" width="100"></el-table-column>
                    <el-table-column property="teacher_name" label="授课教师" width="100"></el-table-column>
                    <el-table-column property="place" label="授课地点" width="100"></el-table-column>
                    <el-table-column property="credit" label="学分" width="80"></el-table-column>
                    <el-table-column property="total_hours" label="总学时" width="80"></el-table-column>
                    <el-table-column property="teaching_hours" label="授课学时" width="80"></el-table-column>
                    <el-table-column property="experiment_hours" label="实验/上机学时" width="120"></el-table-column>
                    <el-table-column property="current" label="已选人数" width="80"></el-table-column>
                    <el-table-column property="capacity" label="选课上限" width="80"></el-table-column>
                    <el-table-column property="comment" label="课程备注"></el-table-column>
                    <!--          选择-->
                    <el-table-column align="right">
                        <template slot-scope="scope">
                            <el-button
                                size="mini"
                                type="success"
                                slot = "reference">
                                <i class="el-icon-position" @click="handleAddSC(scope.row)"></i>选择
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="padding: 10px;text-align: center">
                    <!--          分页控制-->
                    <el-pagination
                        @size-change="handleInnerSizeChange"
                        @current-change="handleInnerCurrentChange"
                        :current-page="innerCurrentPage"
                        :page-sizes="[2, 5, 10, 15, 20]"
                        :page-size="innerPageSize"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="innerTotal">
                    </el-pagination>
                </div>

        </el-dialog>
    </div>
</template>

<script>
import teacher from "@/views/Teacher.vue";

export default {
    name: "Admin",
    computed: {
        teacher() {
            return teacher
        }
    },
    data() {
        const item = {

        };
        return {
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
                label: '无'
            }],
            student_id: '',
            student_name: '',
            teacher_id: '',
            teacher_name: '',
            course_id: '',
            course_name: '',
            type: '',
            place: '',
            credit: '',
            form : {
                student_id: '',
                student_name: '',
                course_id: '',
                course_name: '',
                grade: '',
            },
            inner_course_id: '',
            inner_course_name: '',
            inner : {
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
            dialogFormVisible: false,
            disableEdit: false,
            tableData: Array(0).fill(item),
            innerTableData: Array(0).fill(item),
            total : 10,
            currentPage: 1,
            pageSize: 5,
            innerCurrentPage: 1,
            innerPageSize: 5,
            innerTotal: 10,
        }
    },
    created() {
        this.load()
    },
    // if search

    methods:{
        collapse(){ //点击收缩按钮触发
            this.isCollapse=!this.isCollapse
            if(this.isCollapse){
                this.collapseBtnClass = 'el-icon-s-unfold'
            }else{
                this.collapseBtnClass = 'el-icon-s-fold'
            }
        },
        load(){

            this.request.get('/api/admin/page/sc', {
                    params: {
                        student_id: (this.student_id === '' ? "" : this.student_id),
                        student_name: (this.student_name === '' ? "" : this.student_name),
                        teacher_id: (this.teacher_id === '' ? "" : this.teacher_id),
                        teacher_name: (this.teacher_name === '' ? "" : this.teacher_name),
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
                .then( res => {
                    if (res.status === 200) {
                        this.tableData = res.data.list
                        this.total = res.data.total
                    } else {
                        this.$message.error(res.msg)
                    }
                })

        },
        innerload() {
            //request tc
            this.request.get('/api/admin/page/tc', {
                    params: {
                        teacher_id:"",
                        teacher_name:"",
                        course_id: (this.inner_course_id === '' ? "" : this.inner_course_id),
                        course_name: (this.inner_course_name === '' ? "" : this.inner_course_name),
                        type: "",
                        place: "",
                        credit: -1,
                        page: this.innerCurrentPage,
                        pageSize: this.innerPageSize
                    }
                }
            )
                .then(res => {
                    if (res.status === 200) {
                        this.innerTableData = res.data.list
                        this.innerTotal = res.data.total
                    } else {
                        this.$message.error(res.msg)
                    }
                },)
        },
        handle_add(id, name) {
            this.disableEdit = false
            this.dialogFormVisible = true
            this.innerload()
            this.form = {
                student_id: id,
                student_name: name,
                course_id: '',
                course_name: '',
                grade: '',
            }
        },
        handleAddSC(row) {
            this.$confirm('确定为该生选该课吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.form.course_id = row.course_id
                this.request.post('/api/admin/sc/save', this.form)
                    .then(res => {
                        if (res.status === 200) {
                            this.$message.success(res.msg)
                            this.load()
                        } else {
                            this.$message.error(res.msg)
                        }
                    })
            }).catch(() => {
            });
        },
        handleEdit(row) {
            this.disableEdit = true
            this.dialogFormVisible = true
            this.form = Object.assign({}, row)
        },
        handleDelete(row) {
            this.request.delete('/api/admin/sc/delete/' + row.student_id + '/' + row.course_id)
                .then(res => {
                    if (res.status === 200) {
                        this.$message.success(res.msg)
                        this.load()
                    } else {
                        this.$message.error(res.msg)
                    }
                })
        },
        save(){
            if (this.disableEdit) {
                this.request.post('/api/admin/sc/update', this.form)
                    .then(res => {
                        if (res.status === 200) {
                            this.$message.success(res.msg)
                            this.dialogFormVisible = false
                            this.load()
                        } else {
                            this.$message.error(res.msg)
                        }
                    })
            } else {
                this.request.post('/api/admin/tc/save', this.form)
                    .then(res => {
                        if (res.status === 200) {
                            this.$message.success(res.msg)
                            this.dialogFormVisible = false
                            this.load()
                        } else {
                            this.$message.error(res.msg)
                        }
                    })
            }
        },
        search(){
            this.load()
        },
        search_course(){
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
        handleInnerSizeChange(pageSize) {
            this.innerPageSize = pageSize
            this.innerload()
        },
        handleInnerCurrentChange(currentPage) {
            this.innerCurrentPage = currentPage
            this.innerload()
        },
    }
}
</script>

<style scoped>

</style>