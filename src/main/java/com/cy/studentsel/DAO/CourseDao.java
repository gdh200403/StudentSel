package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.CourseRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository(value = "CourseDao")
public interface CourseDao {
    List<CourseRecord> queryAllCourse();

    public CourseRecord queryCourseByID(@Param("CoursePlan_id") String ID);

    public int updateCourse(CourseRecord courseRecord);

    public int deleteCourse(@Param("course_id") String ID);

    public int addCourse(CourseRecord courseRecord);
}
