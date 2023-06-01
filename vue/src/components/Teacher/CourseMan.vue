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
          <el-select v-model="type" placeholder="请选择课程性质" style="width: 100px" class="ml-5">
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
          <el-input
              placeholder="请输入授课地点"
              prefix-icon="el-icon-search"
              v-model="place"
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
          <!--          搜索按钮-->
          <el-button type="primary" icon="el-icon-search" class="ml-5" @click="search">搜索</el-button>
        </div>
    <!--        新增按钮-->
    <div style="margin: 10px 0">
      <el-button type="primary" class="ml-5" @click="handle_add">新增<i class="el-icon-circle-plus-outline"></i> </el-button>
    </div>
    <!--        数据表格-->
    <el-table :data="tableData"  stripe class="ml-5" height="700" style="width: 100%">
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
      <el-table-column property="experiment_hours" label="实验/上机学时" width="80"></el-table-column>
      <el-table-column property="current" label="已选人数" width="80"></el-table-column>
      <el-table-column property="capacity" label="选课上限" width="80"></el-table-column>
      <el-table-column property="comment" label="课程备注"></el-table-column>
      <!--          点开查看学生-->
      <el-table-column align="right">
        <template slot-scope="scope">
          <el-button
              size="mini"
              @click="handleCheck(scope.row)"
              type="success">
            <i class="el-icon-edit-outline"></i>查看
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
    <!--        编辑对话框-->
    <el-dialog
        title="授课信息"
        :visible.sync="dialogFormVisible"
        width="60%">
        <el-table :data="tableData"  stripe class="ml-5" height="700" style="width: 100%">
            <el-table-column property="还不会调用学生列表" label="一些学生信息" width="100"></el-table-column>
<!--            TODO: 这里会是学生列表-->
            <!--          编辑与删除-->
            <el-table-column align="right">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleGiveScore()"
                            type="success">
                        <i class="el-icon-edit-outline"></i>修改给分
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="padding: 10px;text-align: center">
            <!--          分页控制-->
            <el-pagination
                    @size-change="handleSizeChangeStudent"
                    @current-change="handleCurrentChangeStudent"
                    :current-page="currentPageStudent"
                    :page-sizes="[2, 5, 10, 15, 20]"
                    :page-size="pageSizeStudent"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "Teacher",
  computed: {
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
      currentPageStudent: 1,
      pageSizeStudent: 5,
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
      //TODO: 不知道咋搞成从teacher的api调用导入课程和学生列表
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
    handle_add() {
        this.disableEdit = false
        this.dialogFormVisible = true
        this.form = {
          teacher_id: '',
          course_id: '',
          current: '',
          capacity: '',
          term: '',
          place: '',
          comment: '',
        }
    },
    handleCheck(row) {
        this.disableEdit = true
        this.dialogFormVisible = true
        this.form = Object.assign({}, row)
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
    handleGiveScore() {
        //TODO: 不会写占个位
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
        this.pageSizeStudent = pageSize
        this.load()
    },
    handleCurrentChangeStudent(currentPage) {
        this.currentPageStudent = currentPage
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