package com.cy.studentsel.handler;

import com.cy.studentsel.entity.AdminRecord;
import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.entity.TCRecord;
import com.cy.studentsel.entity.TeacherRecord;

import java.util.List;

/**
 * @author leaf-fulture
 * @date 2023/5/20 21:54
 */
public interface AdminHandler extends UserHandler {
    public AdminRecord getAdmin(String ID);

    public boolean update(String ID, String pwd);

    public List<StudentRecord> queryAllStudent();

    public List<StudentRecord> queryStudentByPage(int page, int size);

    public List<StudentRecord> queryStudentByCondition(StudentRecord record);

    public void addStudent(StudentRecord record);

    public void updateStudent(StudentRecord record);

    public void deleteStudent(String ID);

    public List<TCRecord> queryTCByCondition(TCRecord record);

    public List<TeacherRecord> queryAllTeacher();

    public List<TeacherRecord> queryTeacherByPage(int page, int size);

    public void addTC(TCRecord record);

    public void updateTC(TCRecord record);

    public void deleteTC(String teacherId, String courseId);

    public void addTeacher(TeacherRecord record);

    public List<TeacherRecord> queryTeacherByCondition(TeacherRecord record);

    public void updateTeacher(TeacherRecord record);

    public void deleteTeacher(String id);
}
