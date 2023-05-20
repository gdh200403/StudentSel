package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.CoursePlanRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "CoursePlanDao")
public interface CoursePlanDao {
    List<CoursePlanRecord> queryAllCoursePlan();

    public CoursePlanRecord queryCoursePlanByID(@Param("courseplan_id") String ID);

    public int updateCoursePlan(CoursePlanRecord studentplanRecord);

    public int deleteCoursePlan(@Param("CoursePlan_id") String ID);

    public int addCoursePlan(CoursePlanRecord courseplanRecord);
}
