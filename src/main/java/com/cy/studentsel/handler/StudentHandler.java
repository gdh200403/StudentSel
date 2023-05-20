package com.cy.studentsel.handler;


import com.cy.studentsel.StudentSelApplication;
import com.cy.studentsel.entity.StudentRecord;

import java.util.List;

/**
 * @author leaf-fulture
 * @date 2023/5/20 14:27
 */
public interface StudentHandler {
    /**
     * 学生登录
     * @param ID
     * @param pwd
     * @return
     */
    public String login(String ID, String pwd);

    public List<StudentRecord> queryAllStudent();

    /**
     * Get student by ID
     */
    public StudentRecord getStudent(String ID);


    /**
     * update student info
     */
    public boolean update(StudentRecord studentRecord);
}
