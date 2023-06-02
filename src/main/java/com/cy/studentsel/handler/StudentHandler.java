package com.cy.studentsel.handler;


import com.cy.studentsel.StudentSelApplication;
import com.cy.studentsel.entity.SCRecord;
import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.entity.TCRecord;

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

    public List<SCRecord> querySCByStudentId(String student_id);



    public List<TCRecord> queryTCByCondition(TCRecord record);

    public List<SCRecord> querySCByStudentIdAndTerm(String studentId, String term);

    public void deleteSC(String studentId, String courseId);

//    public SCRecord querySCByStudentID(String studentId);
}
