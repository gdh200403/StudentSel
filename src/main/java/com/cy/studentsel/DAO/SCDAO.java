package com.cy.studentsel.DAO;

import com.cy.studentsel.entity.SCRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository(value = "SCDAO")
public interface SCDAO {
    List<SCRecord> queryAllSC();

    public  List <SCRecord> querySCByStudentID(String student_id);

    public  List <SCRecord> querySCByCourseID(String course_id);

    public  SCRecord querySCByStudentIDAndCourseID(@Param("student_id") String student_id, @Param("course_id") String course_id);

    public void updateSC(SCRecord id);

    public void addSC(SCRecord record);

    public List<SCRecord> querySCByCondition(SCRecord record);

    public void deleteSC(String studentId, String courseId);
}
