package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.CourseRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository(value = "CourseDAO")
public interface CourseDAO {
    List<CourseRecord> queryAllCourse();

    public CourseRecord queryCourseByID(@Param("course_id") String ID);

    public int updateCourse(CourseRecord courseRecord);

    public int deleteCourse(@Param("course_id") String ID);

    public int addCourse(CourseRecord courseRecord);
}
