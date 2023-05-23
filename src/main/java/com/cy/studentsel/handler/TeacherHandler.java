package com.cy.studentsel.handler;

import com.cy.studentsel.entity.TeacherRecord;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author leaf-fulture
 * @date 2023/5/20 17:51
 */

public interface TeacherHandler extends UserHandler {
    public TeacherRecord getTeacher(String ID);

    public boolean update(TeacherRecord teacherRecord);
    public List<TeacherRecord> queryAllTeacher();
}
