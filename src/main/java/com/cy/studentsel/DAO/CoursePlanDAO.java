package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.CoursePlanRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "CoursePlanDAO")
public interface CoursePlanDAO {
    List<CoursePlanRecord> queryAllCoursePlan();

    public CoursePlanRecord queryCoursePlanByID(@Param("course_plan_id") String ID);

    public int updateCoursePlan(CoursePlanRecord record);

    public int deleteCoursePlan(@Param("course_plan_id") String ID);

    public int addCoursePlan(CoursePlanRecord record);
}
