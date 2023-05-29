package com.cy.studentsel.handler.impl;

import com.cy.studentsel.DAO.*;
import com.cy.studentsel.entity.*;
import com.cy.studentsel.handler.AdminHandler;
import com.cy.studentsel.handler.ex.PasswordNoMatchException;
import com.cy.studentsel.handler.ex.UserIdDuplicateException;
import com.cy.studentsel.handler.ex.UserNameNoFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author leaf-fulture
 * @date 2023/5/20 21:55
 */
@Service
public class AdminHandlerImpl implements AdminHandler {
    @Resource
    private AdminDAO adminDAO;
    @Resource
    private StudentDAO studentDAO;
    @Resource
    private TeacherDAO teacherDAO;
    @Resource
    private TCDAO tcDAO;
    @Resource
    private SCDAO scDAO;
    @Resource
    private CourseDAO courseDAO;

    @Override
    public String login(String ID, String pwd) {
        AdminRecord admin = adminDAO.queryAdminByID(ID);
        if (admin == null) {
            throw new UserNameNoFoundException("管理员账号不存在");
        }
        if (!Objects.equals(admin.getPwd(), pwd)){
            throw new PasswordNoMatchException("密码错误");
        }
        return "登录成功";
    }

    @Override
    public AdminRecord getAdmin(String ID) {
        return adminDAO.queryAdminByID(ID);
    }

    @Override
    public boolean update(String ID, String pwd) {
        adminDAO.updateAdmin(new AdminRecord(ID, pwd));
        return true;
    }

    @Override
    public List<StudentRecord> queryAllStudent() {
        return studentDAO.queryAllStudent();
    }

    @Override
    public List<StudentRecord> queryStudentByPage(int page, int size) {
        return studentDAO.queryStudentByPage(page, size);
    }

    @Override
    public List<StudentRecord> queryStudentByCondition(StudentRecord record) {
        return studentDAO.queryStudentByCondition(record);
    }

    @Override
    public void addStudent(StudentRecord record) {
        StudentRecord query = studentDAO.queryStudentByID(record.getStudent_id());
        if (query != null) {
            throw new UserIdDuplicateException("学号已存在");
        }
        studentDAO.addStudent(record);
    }

    @Override
    public void updateStudent(StudentRecord record) {
        studentDAO.updateStudent(record);
    }

    @Override
    public void deleteStudent(String ID) {
        studentDAO.deleteStudent(ID);
    }

    @Override
    public List<TCRecord> queryTCByCondition(TCRecord record) {
        return tcDAO.queryTCByCondition(record);
    }

    @Override
    public List<TeacherRecord> queryAllTeacher() {
        return teacherDAO.queryAllTeacher();
    }

    @Override
    public List<TeacherRecord> queryTeacherByPage(int page, int size) {
        return teacherDAO.queryTeacherByPage(page, size);
    }

    @Override
    public void addTC(TCRecord record) {
        TCRecord query = tcDAO.queryTCByTeacherIDAndCourseID(record.getTeacher_id(), record.getCourse_id());
        if (query != null) {
            throw new UserIdDuplicateException("该教师已教授该课程");
        }
        tcDAO.addTC(record);
    }

    @Override
    public void updateTC(TCRecord record) {
        tcDAO.updateTC(record);
    }

    @Override
    public void deleteTC(String teacherId, String courseId) {
        tcDAO.deleteTC(teacherId, courseId);
    }

    @Override
    public void addTeacher(TeacherRecord record) {
        TeacherRecord query = teacherDAO.queryTeacherByID(record.getTeacher_id());
        if (query != null) {
            throw new UserIdDuplicateException("教师号已存在");
        }
        teacherDAO.addTeacher(record);
    }

    @Override
    public List<TeacherRecord> queryTeacherByCondition(TeacherRecord record) {
        return teacherDAO.queryTeacherByCondition(record);
    }

    @Override
    public void updateTeacher(TeacherRecord record) {
        teacherDAO.updateTeacher(record);
    }

    @Override
    public void deleteTeacher(String id) {
        teacherDAO.deleteTeacher(id);
    }

    @Override
    public List<SCRecord> querySCByCondition(SCRecord record) {
        return scDAO.querySCByCondition(record);
    }

    @Override
    public void addSC(SCRecord record) {
        SCRecord query = scDAO.querySCByStudentIDAndCourseID(record.getStudent_id(), record.getCourse_id());
        if (query != null) {
            throw new UserIdDuplicateException("该学生已选该课程");
        }
        scDAO.addSC(record);
    }

    @Override
    public void updateSC(SCRecord record) {
        scDAO.updateSC(record);
    }

    @Override
    public void deleteSC(String studentId, String courseId) {
        scDAO.deleteSC(studentId, courseId);
    }

    @Override
    public List<CourseRecord> queryCourseByCondition(CourseRecord record) {
        return courseDAO.queryCourseByCondition(record);
    }

    @Override
    public void addCourse(CourseRecord record) {
        CourseRecord query = courseDAO.queryCourseByID(record.getCourse_id());
        if (query != null) {
            throw new UserIdDuplicateException("课程号已存在");
        }
        courseDAO.addCourse(record);
    }

    @Override
    public void updateCourse(CourseRecord record) {
        courseDAO.updateCourse(record);
    }

    @Override
    public void deleteCourse(String id) {
        courseDAO.deleteCourse(id);
    }
}
