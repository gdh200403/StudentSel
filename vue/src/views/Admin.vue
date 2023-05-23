<template>
  <el-container style="height: 100%">
<!--    左侧边栏导航菜单-->
    <el-aside width=" sideWidth + 'px' " style="background-color: rgb(238, 241, 246);height: 100%;box-shadow: 2px 0 6px rgb(0 21 41/35%);">
      <el-menu :default-openeds="['1', '3']" style="min-height: 100vh;overflow-x: hidden;border-right-color: transparent"
      background-color="rgb(48,65,86)"
      text-color="#fff"
      active-text-color="#ffd04b"
      :collapse-transition="false"
      :collapse="isCollapse">
        <div style="height: 60px;line-height: 60px;text-align: center">
          <img src="../assets/logo.png" style="width: 20px;position:relative; top: 5px;margin-right: 5px">
          <b style="color: white" v-show="!isCollapse">选课管理系统</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>
            <span>导航一</span>
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="1-1">选项1</el-menu-item>
            <el-menu-item index="1-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="1-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="1-4">
            <template slot="title">选项4</template>
            <el-menu-item index="1-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span>导航二</span>
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="2-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="2-4">
            <template slot="title">选项4</template>
            <el-menu-item index="2-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span>导航三</span>
          </template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="3-1">选项1</el-menu-item>
            <el-menu-item index="3-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="3-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="3-4">
            <template slot="title">选项4</template>
            <el-menu-item index="3-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
<!--      顶栏-->
      <el-header style="font-size: 12px;border-bottom: 1px solid #ccc;display: flex">
        <div style="flex: 1;font-size: 18px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="text-align:left;cursor: pointer">
          <div>
            <span>王小虎</span>
            <i class="el-icon-arrow-down"></i>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><i class="el-icon-user"></i>个人信息</el-dropdown-item>
            <el-dropdown-item><i class="el-icon-close"></i>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
<!--      主体内容-->
      <el-main style="overflow-x: hidden">
        <div style="margin: 10px 0">
<!--          搜索框-->
          <el-input
              placeholder="请输入ID"
              prefix-icon="el-icon-search"
              v-model="input1"
              style="width: 200px;"
              class="ml-5">
          </el-input>
          <el-input
            placeholder="请输入姓名"
            prefix-icon="el-icon-search"
            v-model="input2"
            style="width: 200px"
            class="ml-5">
        </el-input>
          <el-input
              placeholder="请输入xx"
              prefix-icon="el-icon-search"
              v-model="input3"
              style="width: 200px"
              class="ml-5">
          </el-input>
<!--          搜索按钮-->
          <el-button type="primary" icon="el-icon-search" class="ml-5">搜索</el-button>
        </div>
<!--        新增与批量删除按钮-->
        <div style="margin: 10px 0">
          <el-button type="primary" class="ml-5">新增<i class="el-icon-circle-plus-outline"></i> </el-button>
          <el-button type="danger" class="ml-5">批量删除<i class="el-icon-circle-remove-outline"></i> </el-button>
        </div>
<!--        数据表格-->
        <el-table :data="tableData"  stripe class="ml-5" height="700" style="width: 100%">
          <el-table-column prop="date" label="日期" width="140">
          </el-table-column>
          <el-table-column prop="name" label="姓名" width="120">
          </el-table-column>
          <el-table-column prop="address" label="地址">
          </el-table-column>
<!--          编辑与删除-->
          <el-table-column
              align="right">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)"
              type="success"><i class="el-icon-edit-outline"></i>编辑</el-button>
              <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)"><i class="el-icon-delete"></i>删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="padding: 10px;text-align: center">
<!--          分页控制-->
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage4"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="10"
              layout="total, sizes, prev, pager, next, jumper"
              :total="400">
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Admin",
  data() {
    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      tableData: Array(10).fill(item),
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
    }
  },
  methods:{
    collapse(){ //点击收缩按钮触发
      this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.collapseBtnClass = 'el-icon-s-unfold'
      }else{
        this.collapseBtnClass = 'el-icon-s-fold'
      }
    }
  }
}
</script>

<style scoped>
.el-header {
  background-color: #ffffff;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>