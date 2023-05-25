package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.TeacherRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository(value = "TeacherDAO")
public interface TeacherDAO {
    List<TeacherRecord> queryAllTeacher();


    public List<TeacherRecord> queryTeacherByPage(@Param("page") int page, @Param("size") int size);

    public TeacherRecord queryTeacherByID(@Param("teacher_id") String ID);


    public int updateTeacher(TeacherRecord record);

    public int deleteTeacher(@Param("teacher_id") String ID);

    public int addTeacher(TeacherRecord record);
}
