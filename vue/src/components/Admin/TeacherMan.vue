<template>
  <div>
    <!--          搜索框-->
    <div style="margin: 10px 0">
      <el-input
          placeholder="请输入工号"
          prefix-icon="el-icon-search"
          v-model="teacher_id"
          style="width: 200px;"
          class="ml-5">
      </el-input>
      <el-input
          placeholder="请输入姓名"
          prefix-icon="el-icon-search"
          v-model="teacher_name"
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
      <!--          搜索按钮-->
      <el-button type="primary" icon="el-icon-search" class="ml-5" @click="search">搜索</el-button>
    </div>
    <!--        新增按钮-->
    <div style="margin: 10px 0">
      <el-button type="primary" class="ml-5" @click="handleAdd">新增<i class="el-icon-circle-plus-outline" @click="handleAdd"></i> </el-button>
    </div>
    <!--        数据表格-->
    <el-table :data="tableData"  stripe class="ml-5" height="700" style="width: 100%">
      <el-table-column prop="teacher_id" label="学号" width="180"></el-table-column>
      <el-table-column prop="teacher_name" label="姓名" width="120"></el-table-column>
      <el-table-column prop="sex" label="性别" width="120"></el-table-column>
      <el-table-column prop="age" label="年龄" width="120"></el-table-column>
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
            title="确定删除该教师信息？"
            @confirm="handleDelete(scope.row.teacher_id)"
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
    <el-dialog title="教师信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="100px">
        <el-form-item label="教师工号">
          <el-input :disabled = "disableEditID" v-model="form.teacher_id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="教师姓名">
          <el-input v-model="form.teacher_name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="教师性别">
          <el-input v-model="form.sex" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="教师年龄">
          <el-input v-model="form.age" autocomplete="off"></el-input>
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
    name: "Admin",
    data() {
      const item = {
      };
      return {
        teacher_id: '',
        teacher_name: '',
        sex: '',
        age: '',
        form : {
          teacher_id: '',
          teacher_name: '',
          sex: '',
          age: '',
          pwd: ''
        },
        tableData: Array(0).fill(item),
        total : 10,
        currentPage: 1,
        pageSize: 5,
        disableEditID: false,
        dialogTableVisible: false,
        dialogFormVisible: false,
        gridData: Array(0).fill(item),//TODO
        teacherName: ''//TODO
      }
    },
  created() {
    this.load()
  },
  // if search

  methods:{
    load(){
      this.request.get('/api/admin/page/teacher', {
          params: {
            page : this.currentPage,
            pageSize : this.pageSize,
            teacher_id : (this.teacher_id=== '') ? "" : this.teacher_id,
            teacher_name : (this.teacher_name=== '') ? "" : this.teacher_name,
            sex : (this.sex === '') ? "" : this.sex,
            age : (this.age === '') ? -1 : this.age,
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
        teacher_id: '',
        teacher_name: '',
        sex: '',
        age: '',
        pwd: '',
      }
    },
    handleEdit(row) {
      this.disableEditID = true
      this.form = Object.assign({}, row)
      this.dialogFormVisible = true
    },
    handleDelete(id) {
      this.request.delete('/api/admin/teacher/delete/' + id)
        .then(res => {
          if (res.status === 200) {
            this.$message.success(res.msg)
            this.load()
          } else {
            this.$message.error(res.msg)
          }
        })
    },
    save() {
      if (this.disableEditID) {
        this.request.post('/api/admin/teacher/update', this.form)
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
        this.request.post('/api/admin/teacher/save', this.form)
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