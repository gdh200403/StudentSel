<template>
  <div>
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
          <el-input
                  placeholder="请输入性别"
                  prefix-icon="el-icon-search"
                  v-model="sex"
                  style="width: 200px"
                  class="ml-5">
          </el-input>
          <el-input
                  placeholder="请输入年龄"
                  prefix-icon="el-icon-search"
                  v-model="age"
                  style="width: 200px"
                  class="ml-5">
          </el-input>
          <el-input
                  placeholder="请输入专业"
                  prefix-icon="el-icon-search"
                  v-model="major"
                  style="width: 200px"
                  class="ml-5">
          </el-input>
          <!--          搜索按钮-->
          <el-button type="primary" icon="el-icon-search" class="ml-5" @click="search">搜索</el-button>
      </div>
    <!--        新增按钮-->
    <div style="margin: 10px 0">
        <el-button type="primary" class="ml-5" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i> </el-button>
    </div>
    <!--        数据表格-->
    <el-table :data="tableData"  stripe class="ml-5" height="700" style="width: 100%">
      <el-table-column prop="student_id" label="学号" width="180"></el-table-column>
      <el-table-column prop="student_name" label="姓名" width="120"></el-table-column>
      <el-table-column prop="sex" label="性别" width="120"></el-table-column>
      <el-table-column prop="age" label="年龄" width="120"></el-table-column>
      <el-table-column prop="major" label="专业" width="120"></el-table-column>
      <!--   查看选课、编辑与删除-->
      <el-table-column align="right">
        <template slot-scope="scope">
          <el-button
                  size="mini"
                  @click="handleEdit(scope.row)"
                  type="success">
            <i class="el-icon-edit-outline"></i>编辑
          </el-button>

            <el-button
                    size="mini"
                    type="danger"
                    >
              <i class="el-icon-delete" @click="handleDelete(scope.row.student_id)"></i>删除
            </el-button>
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
    <!--    查看选课对话框 是否要在这个对话框中实现对该学生选课的增删？-->
    <el-dialog :title="stuName" :visible.sync="dialogTableVisible">
      <el-table :data="gridData">
        <el-table-column property="courseID" label="课程ID" width="150"></el-table-column>
        <el-table-column property="courseName" label="课程名称" width="200"></el-table-column>
        <el-table-column property="courseType" label="课程性质" width="100"></el-table-column>
        <el-table-column property="courseTeacher" label="授课教师" width="200"></el-table-column>
        <el-table-column property="courseTeacher" label="授课时间" width="200"></el-table-column>
        <el-table-column property="courseTeacher" label="授课地点" width="200"></el-table-column>
        <el-table-column property="credit" label="学分" width="80"></el-table-column>
        <el-table-column property="totalHours" label="总学时" width="100"></el-table-column>
        <el-table-column property="classHours" label="授课学时" width="100"></el-table-column>
        <el-table-column property="labHours" label="实验/上机学时" width="100"></el-table-column>
        <el-table-column property="occupied" label="已选人数" width="100"></el-table-column>
        <el-table-column property="capacity" label="选课上限" width="100"></el-table-column>
        <el-table-column property="comment" label="课程备注"></el-table-column>
      </el-table>
    </el-dialog>
    <!--    增改学生信息对话框-->
    <el-dialog title="学生信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form label-width="100px">
            <el-form-item label="学生学号">
                <el-input :disabled = "disableEditID" v-model="form.student_id" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="学生姓名">
                <el-input v-model="form.student_name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="学生性别">
                <el-input v-model="form.sex" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="学生年龄">
                <el-input v-model="form.age" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="学生专业">
                <el-input v-model="form.major" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码">
                <el-input v-model="form.pwd" autocomplete="off"></el-input>
            </el-form-item>
            <!--                        <el-form-item label="活动区域">-->
            <!--                            <el-select v-model="form.region" placeholder="请选择活动区域">-->
            <!--                                <el-option label="区域一" value="shanghai"></el-option>-->
            <!--                                <el-option label="区域二" value="beijing"></el-option>-->
            <!--                            </el-select>-->
            <!--                        </el-form-item>-->
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
        </div>
  </el-dialog>
  </div>
</template>

<script>
export default {
    name: "User.vue",
    data() {
        const item = {
            student_id: '1',
            student_name: '12',
            sex: '123',
            age: '12',
            major: '12',
        };
        return {
            student_id: '',
            student_name: '',
            sex: '',
            age: '',
            major: '',
            stuName: '',//TODO 获取当前学生姓名
            gridData: Array(0).fill(item),//TODO
            dialogTableVisible: false,
            dialogFormVisible : false,
            disableEditID : false,
            form: {
                student_id: '',
                student_name: '',
                sex: '',
                age: '',
                major: '',
                pwd: '123456',
            },
            tableData: Array(0).fill(item),
            total : 10,
            currentPage: 1,
            pageSize: 5,
        }
    },
    created() {
        this.load()
    },
    methods: {
        load(){
            this.request.get('/api/admin/page/student', {
                    params: {
                        page : this.currentPage,
                        pageSize : this.pageSize,
                        student_id : (this.student_id === '') ? "" : this.student_id,
                        student_name : (this.student_name === '') ? "" : this.student_name,
                        sex : (this.sex === '') ? "" : this.sex,
                        age : (this.age === '') ? -1 : this.age,
                        major : (this.major === '') ? "" : this.major,
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
        search(){
            this.load()
        },
        handleAdd() {
            this.disableEditID = false
            this.dialogFormVisible = true
            this.form =  {
                student_id: '',
                student_name: '',
                sex: '',
                age: '',
                major: '',
                pwd: '',
            }
        },
        handleEdit(row) {
            this.disableEditID = true
            this.form = Object.assign({}, row)
            this.dialogFormVisible = true
        },
        handleDelete(id) {
            this.$confirm('删除该生会删除与该学生相关选课信息，确定吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'danger'
            }).then(() => {
                this.request.delete('/api/admin/student/delete/' + id)
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
        save() {
            if (this.disableEditID) {
                this.request.post('/api/admin/student/update', this.form)
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
                this.request.post('/api/admin/student/save', this.form)
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

        handleSizeChange(pageSize) {
            this.pageSize = pageSize
            this.load()
        },
        handleCurrentChange(currentPage) {
            this.currentPage = currentPage
            this.load()
        },
    }
}
</script>

<style scoped>

</style>