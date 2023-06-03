package com.cy.studentsel.handler;

import com.cy.studentsel.entity.SCRecord;
import com.cy.studentsel.entity.TCRecord;
import com.cy.studentsel.entity.TeacherRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author leaf-fulture
 * @date 2023/5/20 17:51
 */

public interface TeacherHandler extends UserHandler {
    List<TCRecord> queryTCByCondition(TCRecord record);

    TeacherRecord getTeacher(String ID);

    boolean update(TeacherRecord teacherRecord);

    TeacherRecord queryTeacherByTeacherID(String teacherId);

    List<SCRecord> querySCByCondition(SCRecord record);

    void updateTC(TCRecord record);

    void updateSC(SCRecord record);
}
