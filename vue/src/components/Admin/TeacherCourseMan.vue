<template>
    <div style="overflow-x: hidden">
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
                placeholder="请输入授课教师ID"
                prefix-icon="el-icon-search"
                v-model="teacher_id"
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
            <el-input
                placeholder="请输入授课地点"
                prefix-icon="el-icon-search"
                v-model="place"
                style="width: 200px"
                class="ml-5">
            </el-input>
            <!--          搜索按钮-->
            <el-button type="primary" icon="el-icon-search" class="ml-5" @click="search">搜索</el-button>
        </div>
<!--        &lt;!&ndash;        新增按钮&ndash;&gt;-->
<!--        <div style="margin: 10px 0">-->
<!--            <el-button type="primary" class="ml-5" @click="handle_add">新增<i class="el-icon-circle-plus-outline"></i> </el-button>-->
<!--        </div>-->
        <!--        数据表格-->
        <el-table :data="tableData"
                  :span-method="objectSpanMethod"
                  stripe class="ml-5" height="700" style="width: 100%">
            <el-table-column property="course_name" label="课程名称" width="300">
                <template slot-scope="scope">
                    <span >{{ scope.row.course_name }}</span>
                    <el-button
                        size="mini"
                        @click="handleAddTC(scope.row)"
                        type="primary"
                    >
                        <i class="el-icon-circle-plus-outline"></i>新增开课
                    </el-button>

                </template>
            </el-table-column>
            <el-table-column property="term" label="授课学期" width="100"></el-table-column>
            <el-table-column property="course_id" label="课程ID" width="100"></el-table-column>
            <el-table-column property="teacher_name" label="授课教师" width="100"></el-table-column>
            <el-table-column property="teacher_id" label="授课教师ID" width="100"></el-table-column>
            <el-table-column property="place" label="授课地点" width="100"></el-table-column>
            <el-table-column property="time" label="上课时间" width="100"></el-table-column>
            <el-table-column property="current" label="已选人数" width="80"></el-table-column>
            <el-table-column property="capacity" label="选课上限" width="80"></el-table-column>
            <el-table-column property="comment" label="课程备注"></el-table-column>
            <!--          编辑与删除-->
            <el-table-column align="right">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="handleEdit(scope.row)"
                        type="success">
                        <i class="el-icon-edit-outline"></i>编辑
                    </el-button>
                    <el-popconfirm
                        class="ml-5"
                        confirm-button-text='确定'
                        cancel-button-text='取消'
                        icon="el-icon-info"
                        icon-color="red"
                        title="确定删除该授课信息吗？"
                        @confirm="handleDelete(scope.row)"
                    >
                        <el-button
                            size="mini"
                            type="danger"
                            slot = "reference">
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
        <!--        编辑对话框-->
        <el-dialog
            title="授课信息"
            :visible.sync="dialogFormVisible"
            width="30%">
            <el-form label-width="100px" class="demo-ruleForm">
                <el-form-item label="课程ID" prop="course_id">
                    <el-input :disabled = "disableEdit" v-model="form.course_id"></el-input>
                </el-form-item>
                <el-form-item label="课程名称" prop="course_name">
                    <el-input  v-model="form.course_name" disabled></el-input>
                </el-form-item>
                <el-form-item label="教师ID" prop="course_id">
                    <el-input :disabled = "disableEdit" v-model="form.teacher_id"></el-input>
                </el-form-item>
                <el-form-item label="教师姓名" prop="course_name">
                    <el-input v-model="form.teacher_name" disabled></el-input>
                </el-form-item>
                <el-form-item label="授课学期" prop="comment">
                    <el-input v-model="form.term"></el-input>
                </el-form-item>
                <!--        <el-form-item label="课程名称" prop="course_name">-->
                <!--          <el-input v-model="form.course_name"></el-input>-->
                <!--        </el-form-item>-->
                <!--        <el-form-item label="课程性质" prop="type">-->
                <!--          <el-select v-model="form.type" placeholder="请选择课程性质">-->
                <!--            <el-option-->
                <!--                v-for="item in options"-->
                <!--                :key="item.value"-->
                <!--                :label="item.label"-->
                <!--                :value="item.value">-->
                <!--            </el-option>-->
                <!--          </el-select>-->
                <!--        </el-form-item>-->
                <el-form-item label="授课地点" prop="place">
                    <el-input v-model="form.place"></el-input>
                </el-form-item>
                <el-form-item label="授课时间" prop="time">
                    <el-input v-model="form.time"></el-input>
                </el-form-item>
                <!--        <el-form-item label="学分" prop="credit">-->
                <!--          <el-input v-model="form.credit"></el-input>-->
                <!--        </el-form-item>-->
                <!--        <el-form-item label="总学时" prop="total_hours">-->
                <!--          <el-input v-model="form.total_hours"></el-input>-->
                <!--        </el-form-item>-->
                <!--        <el-form-item label="授课学时" prop="teaching_hours">-->
                <!--          <el-input v-model="form.teaching_hours"></el-input>-->
                <!--        </el-form-item>-->
                <!--        <el-form-item label="实验/上机学时" prop="experiment_hours">-->
                <!--          <el-input v-model="form.experiment_hours"></el-input>-->
                <!--        </el-form-item>-->
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
            teacher_id: '',
            teacher_name: '',
            course_id: '',
            course_name: '',
            term:'',
            type: '',
            place: '',
            credit: '',
            form : {
                teacher_id: '',
                teacher_name: '',
                course_id: '',
                course_name: '',
                // type: '',
                // credit: '',
                // total_hours: '',
                // teaching_hours: '',
                // experiment_hours: '',
                current: '',
                capacity: '',
                term: '',
                place: '',
                comment: '',
            },
            dialogFormVisible: false,
            disableEdit: false,
            tableData: Array(0).fill(item),
            total : 10,
            currentPage: 1,
            pageSize: 5,
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

            this.request.get('/api/admin/page/tc', {
                    params: {
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
        handleAddTC() {
            this.disableEdit = false
            this.dialogFormVisible = true
            // this.form.course_name = row.course_name
            this.form = {
                teacher_id: '',
                course_id: '',
                current: '',
                capacity: '',
                term: '',
                place: '',
                comment: '',
                time:'',
            }
        },
        handleEdit(row) {
            this.disableEdit = true
            this.dialogFormVisible = true
            this.form = Object.assign({}, row)
        },
        handleDelete(row) {
            this.request.delete('/api/admin/tc/delete/' + row.teacher_id + '/' + row.course_id)
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
                this.request.post('/api/admin/tc/update', this.form)
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
        handleSizeChange(pageSize) {
            this.pageSize = pageSize
            this.load()
        },
        handleCurrentChange(currentPage) {
            this.currentPage = currentPage
            this.load()
        },
        objectSpanMethod({ row, column, rowIndex, columnIndex }) {//合并课程名相同项
            // 判断是否为第一列
            if (columnIndex === 0) {
                // 判断当前行是否需要合并
                if (rowIndex === 0 || row.course_name !== this.tableData[rowIndex - 1].course_name) {
                    let rowspan = 1;
                    for (let i = rowIndex + 1; i < this.tableData.length; i++) {
                        if (row.course_name === this.tableData[i].course_name) {
                            rowspan++;
                        } else {
                            break;
                        }
                    }
                    return { rowspan, colspan: 1 };
                } else {
                    return { rowspan: 0, colspan: 0 };
                }
            }
            // 判断是否为第二列
            if (columnIndex === 1) {
                // 判断当前行是否需要合并
                if (rowIndex === 0 || row.term !== this.tableData[rowIndex - 1].term) {
                    let rowspan = 1;
                    for (let i = rowIndex + 1; i < this.tableData.length; i++) {
                        if (row.term === this.tableData[i].term) {
                            rowspan++;
                        } else {
                            break;
                        }
                    }
                    return { rowspan, colspan: 1 };
                } else {
                    return { rowspan: 0, colspan: 0 };
                }
            }
        }
    }
}
</script>

<style scoped>

</style>