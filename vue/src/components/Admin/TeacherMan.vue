<template>
  <div>
    <!--          搜索框-->
    <div style="margin: 10px 0">
      <el-input
          placeholder="请输入ID"
          prefix-icon="el-icon-search"
          v-model="id"
          style="width: 200px;"
          class="ml-5">
      </el-input>
      <el-input
          placeholder="请输入姓名"
          prefix-icon="el-icon-search"
          v-model="name"
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
      <el-button type="primary" class="ml-5">新增<i class="el-icon-circle-plus-outline"></i> </el-button>
    </div>
    <!--        数据表格-->
    <el-table :data="tableData"  stripe class="ml-5" height="700" style="width: 100%">
      <el-table-column prop="id" label="学号" width="180"></el-table-column>
      <el-table-column prop="name" label="姓名" width="120"></el-table-column>
      <el-table-column prop="sex" label="性别" width="120"></el-table-column>
      <el-table-column prop="age" label="年龄" width="120"></el-table-column>
      <el-table-column prop="major" label="专业" width="120"></el-table-column>
      <!--          编辑与删除-->
      <el-table-column align="right">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="dialogTableVisible = true"
              type="info">
            <i class="el-icon-tickets"></i>查看开课
          </el-button>
          <el-dialog :title="teacherName" :visible.sync="dialogTableVisible">
            <el-table :data="gridData">
              <el-table-column property="courseID" label="课程ID" width="150"></el-table-column>
              <el-table-column property="courseName" label="课程名称" width="200"></el-table-column>
              <el-table-column property="courseType" label="课程性质" width="100"></el-table-column>
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
          <el-button
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
              type="success">
            <i class="el-icon-edit-outline"></i>编辑
          </el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">
            <i class="el-icon-delete"></i>删除
          </el-button>
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
  </div>
</template>

<script>
  export default {
    name: "Admin",
    data() {
      const item = {
      };
      return {
        id: '',
        name: '',
        sex: '',
        age: '',
        major: '',
        tableData: Array(0).fill(item),
        total : 10,
        currentPage: 1,
        pageSize: 5,
        dialogTableVisible: false,
        gridData: Array(0).fill(item),//TODO
        teacherName: ''//TODO
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
      this.request.get('/api/admin/page/student', {
            params: {
              page : this.currentPage,
              pageSize : this.pageSize,
              id : (this.id === '') ? "" : this.id,
              name : (this.name === '') ? "" : this.name,
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