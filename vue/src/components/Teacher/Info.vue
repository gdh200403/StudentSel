<script>
import {defineComponent} from 'vue'

export default defineComponent({
    name: "info",
    data() {
        return {
            user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : {},
            teacher: {
                teacher_id: '',
                teacher_name: '',
                sex: '',
                age: '',
            },

            tableData: [],
        }
    },
    created() {
        this.load()
    },
    methods: {
        load() {
            //request for student Personal info
            this.request.get('/api/teacher/personal', {
                params: {
                    teacher_id: this.user.username,
                }
            }).then(res => {
                console.log(res)
                if (res.status === 200) {
                    this.teacher = res.data
                }
                else {
                    if (res.status === 401) {
                        this.$router.push('/login')
                    }
                    this.$message({
                        message: res.msg,
                        type: 'error'
                    })
                }
            }).catch(err => {
                console.log(err)
            })
        },
    }
})
</script>

<template>
    <div>
        <el-descriptions class="info" title="个人信息" :column="3" border>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    工号
                </template>
                {{teacher.teacher_id}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    姓名
                </template>
                {{teacher.teacher_name}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    性别
                </template>
                {{teacher.sex}}
            </el-descriptions-item>
            <el-descriptions-item>
                <template slot="label">
                    <i class="el-icon-user"></i>
                    年龄
                </template>
                {{teacher.age}}
            </el-descriptions-item>
        </el-descriptions>

    </div>
</template>

<style scoped>
.info{
    width: 90%;
    display: block;
    margin: auto;
}
</style>