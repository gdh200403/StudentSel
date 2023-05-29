package com.cy.studentsel.handler;


import com.cy.studentsel.StudentSelApplication;
import com.cy.studentsel.entity.SCRecord;
import com.cy.studentsel.entity.StudentRecord;

import java.util.List;

/**
 * @author leaf-fulture
 * @date 2023/5/20 14:27
 */
public interface StudentHandler extends UserHandler {

    /**
     * Get student by ID
     */
    public StudentRecord getStudent(String ID);


    /**
     * update student info
     */
    public boolean update(StudentRecord studentRecord);

    public void addSC(SCRecord record);

//    public SCRecord querySCByStudentID(String studentId);
}
