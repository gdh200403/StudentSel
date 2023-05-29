package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.CourseRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository(value = "CourseDAO")
public interface CourseDAO {
    List<CourseRecord> queryAllCourse();

    public CourseRecord queryCourseByID(@Param("course_id") String ID);

    public List<CourseRecord> queryCourseByCondition(CourseRecord record);

    public int updateCourse(CourseRecord record);

    public int deleteCourse(@Param("course_id") String ID);

    @Insert("insert into course (course_id, course_name, type, credit, total_hours, teaching_hours, experiment_hours) " +
            "values(#{course_id}, #{course_name}, #{type}, #{credit}, #{total_hours}, #{teaching_hours}, #{experiment_hours})")
    public int addCourse(CourseRecord record);
}
