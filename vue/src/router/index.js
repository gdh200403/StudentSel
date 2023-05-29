import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'home',
        component: () => import('../views/Home.vue')
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/admin',
        name: 'admin',
        component: () => import('../views/Admin.vue'),

        children: [
            {path: 'studentMan', name: 'studentMan', component: () => import('../components/Admin/StudentMan.vue')},
            {path: 'teacherMan', name: 'teacherMan', component: () => import('../components/Admin/TeacherMan.vue')},
            {path: 'courseMan', name: 'courseMan', component: () => import('../components/Admin/CourseMan.vue')},
            {path: 'studentCourseMan', name: 'studentCourseMan', component: () => import('../components/Admin/SCMan.vue')},
            {path: 'teacherCourseMan', name: 'teacherCourseMan', component: () => import('../components/Admin/TCMan.vue')},
        ]
    },
    {
        path: '/teacher',
        name: 'teacher',
        component: () => import('../views/Teacher.vue')
    },
    {
        path: '/demo',
        name: 'demo',
        component: () => import('../views/demo.vue')
    },
    {
        path: '/404',
        name: '404',
        component: () => import('../views/404.vue')
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
