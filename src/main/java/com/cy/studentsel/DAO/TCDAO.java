package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.TCRecord;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "TCDAO")
public interface TCDAO {
    @Select("select * from tc, teacher, course where tc.teacher_id = teacher.teacher_id and tc.course_id = course.course_id")
    List<TCRecord> queryAllTC();

    TCRecord queryTCByTeacherIDAndCourseID(@Param("teacher_id") String teacher_id, @Param("course_id") String course_id);

    List<TCRecord> queryTCByCondition(TCRecord record);

    @Insert("insert into tc(teacher_id, course_id, current, Capacity, term, place, comment) " +
            "values(#{teacher_id}, #{course_id}, #{current}, #{capacity}, #{term}, #{place}, #{comment})")
    void addTC(TCRecord record);

    void updateTC(TCRecord record);

    @Delete("delete from tc where teacher_id = #{teacherId} and course_id = #{courseId}")
    void deleteTC(String teacherId, String courseId);
//    public List <TCRecord> queryTCByTeacherID(@Param("teacher_id") String ID);
//
//    public List <TCRecord> queryTCByCourseID(@Param("course_id") String ID);
//
//    public List <TCRecord> queryTCByTeacherIDAndCourseID(TCRecord tc);
//
//    public void deleteTC(@Param("teacher_id") String teacher_id,@Param("course_id") String course_id);
//
//    public void addTC(TCRecord tc);
}
