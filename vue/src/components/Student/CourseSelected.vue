<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "CourseSelected",
    data() {
        return {
            user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {},
            current_term: 'sp23',
            tableData: [],
        }
    },
    created() {
      this.load()
    },
    methods: {
        load() {
            // request for student course info
            this.request.get('/api/student/current/sc', {
                params: {
                    student_id: this.user.username,
                    term: this.current_term,
                }
            }).then(res => {
                console.log(res)
                this.tableData = res.data

            }).catch(err => {
                console.log(err)
            })
        },
        quitCourse(row) {
            this.$confirm('退课后人满可能导致无法再选上，确定吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'danger'
            }).then(() => {
                this.request.delete('/api/student/sc/delete/'+this.user.username+'/'+row.course_id).then(res => {
                    if (res.status === 200) {
                        this.$message.success('退课成功')
                        this.load()
                    } else {
                        this.$message.error(res.msg)
                    }
                })
            }).catch(() => {
            });

        }
    }
})
</script>

<template>
    <el-table :data="tableData" style="width: 100%" height="85vh" >
        <el-table-column prop="course_id" label="课程ID"></el-table-column>
        <el-table-column prop="course_name" label="课程名称"></el-table-column>
        <el-table-column prop="term" label="授课学期"></el-table-column>
        <el-table-column prop="type" label="课程种类"></el-table-column>
        <el-table-column prop="teacher_name" label="授课教师"></el-table-column>
        <el-table-column prop="credit" label="学分"></el-table-column>
        <el-table-column prop="total_hours" label="总学时"></el-table-column>
        <el-table-column prop="teaching_hours" label="授课学时"></el-table-column>
        <el-table-column prop="experiment_hours" label="实验学时"></el-table-column>
        <el-table-column prop="time" label="上课时间"></el-table-column>
        <el-table-column prop="place" label="上课地点"></el-table-column>
        <el-table-column prop="current" label="已选人数" fixed="right" width="70"></el-table-column>
        <el-table-column prop="capacity" label="选课上限" fixed="right" width="70"></el-table-column>
        <el-table-column prop="status" label="选课状态" fixed="right" width="100"><template slot-scope="{ row }">
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
                <el-button v-if="!row.selected" type="primary" @click="quitCourse(row)" size="mini">退课</el-button>
                <el-tag v-else>退课成功</el-tag>
            </template>
        </el-table-column>
    </el-table>
</template>

<style scoped>

</style>