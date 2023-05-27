<template>
  <div style="overflow-x: hidden">
    <!--          搜索框-->
    <div style="margin: 10px 0">
          <el-input
              placeholder="请输入课程ID"
              prefix-icon="el-icon-search"
              v-model="courseID"
              style="width: 200px;"
              class="ml-5">
          </el-input>
          <el-input
              placeholder="请输入课程名称"
              prefix-icon="el-icon-search"
              v-model="courseName"
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
          <el-select v-model="value" placeholder="请选择课程性质" style="width: 200px" class="ml-5">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
          <!--          搜索按钮-->
          <el-button type="primary" icon="el-icon-search" class="ml-5" @click="search">搜索</el-button>
        </div>
    <!--        新增按钮-->
    <div style="margin: 10px 0">
      <el-button type="primary" class="ml-5">新增<i class="el-icon-circle-plus-outline"></i> </el-button>
    </div>
    <!--        数据表格-->
    <el-table :data="tableData"  stripe class="ml-5" height="700" style="width: 100%">
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
      <!--          编辑与删除-->
      <el-table-column align="right">
        <template slot-scope="scope">
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
      options: [{
        value: '选项1',
        label: '公共课'
      }, {
        value: '选项2',
        label: '必修课'
      }, {
        value: '选项3',
        label: '选修课'
      }],
      courseID: '',
      courseName: '',
      major: '',
      value: '',
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

.menu-header{
  height: 60px;
  line-height: 60px;
  text-align: center;
}

.logo {
  width: 18px;
  position:relative;
  top: 3px;
  margin-right: 5px;
}
</style>