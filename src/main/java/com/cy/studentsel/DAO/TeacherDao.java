package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.TeacherRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository(value = "TeacherDao")
public interface TeacherDao {
    List<TeacherRecord> queryAllTeacher();

    public TeacherRecord queryTeacherByID(@Param("Teacher_id") String ID);

    public int updateTeacher(TeacherRecord teacherRecord);

    public int deleteTeacher(@Param("teacher_id") String ID);

    public int addTeacher(TeacherRecord teacherRecord);
}
