<template>
    <div class="login-container">
        <el-card class="login-card" shadow="hover">
            <h1 class="login-title">选课系统登录</h1>
            <el-form ref="form" :model="form">
                <el-form-item prop="username">
                    <el-input
                        v-model="form.username"
                        clearable
                        placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        v-model="form.password"
                        clearable
                        placeholder="请输入密码"
                        show-password>
                    </el-input>
                </el-form-item>
                <el-form-item class="radio-group" prop="userType">
                    <el-radio-group v-model="form.userType">
                        <el-radio label="student">学生</el-radio>
                        <el-radio label="teacher">教师</el-radio>
                        <el-radio label="admin">管理员</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item class="login-button">
                    <el-button type="primary" @click="login">登录</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
export default {
    data() {
        return {
            form: {
                username: '',
                password: '',
                userType: ''
            }
        }
    },
    methods: {
        login() {
            localStorage.removeItem('user')
            this.$refs.form.validate(valid => {
                if (valid) {
                    this.request.post("/api/verify", this.form).then(res => {
                        console.log(res)
                        if (res.status === 200) {
                            this.$message({
                                message: '登录成功',
                                type: 'success'
                            })
                            localStorage.setItem('user', JSON.stringify(res.data))
                            this.$router.push({path: '/' + this.form.userType})
                        } else {
                            this.$message({
                                message: res.msg,
                                type: 'error'
                            })
                        }
                    })
                    console.log('登录成功')
                } else {
                    console.log('表单验证失败')
                    return false
                }
            })
        }
    }
}
</script>

<style scoped>
h1 {
    text-align: center;
}

.login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
    overflow: hidden;
}

.login-card {
    width: 400px;
}

.login-title {
    font-size: 24px;
    margin-bottom: 20px;
}

.radio-group {
    display: flex;
    justify-content: center;
    align-items: center;
}

.login-button {
    display: flex;
    justify-content: center;
}

.wrapper {
}
</style>
