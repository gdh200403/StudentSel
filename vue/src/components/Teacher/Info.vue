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
                this.teacher = res.data
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
            <template slot="extra">
                <el-button type="primary" size="small">编辑</el-button>
            </template>
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