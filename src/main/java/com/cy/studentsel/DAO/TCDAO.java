package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.TCRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "TCDAO")
public interface TCDAO {
    List<TCRecord> queryAllTC();

    public List <TCRecord> queryTCByTeacherID(@Param("teacher_id") String ID);

    public List <TCRecord> queryTCByCourseID(String course_id);

    public List <TCRecord> queryTCByTeacherIDAndCourseID( TCRecord tc);

    public void deleteTC(String courseid);
    public void addTC(TCRecord tc);
}
