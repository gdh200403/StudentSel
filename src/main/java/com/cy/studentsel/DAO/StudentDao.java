package com.cy.studentsel.DAO;



import com.cy.studentsel.entity.StudentRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author leaf-fulture
 * @date 2023/5/19 21:11
 */
@Mapper
@Repository(value = "StudentDao")
public interface StudentDao {
    public List<StudentRecord> queryAllStudent();

    public StudentRecord queryStudentByID(@Param("student_id") String ID);

    public int updateStudent(StudentRecord studentRecord);

    public int deleteStudent(@Param("student_id") String ID);

    public int addStudent(StudentRecord studentRecord);
}

