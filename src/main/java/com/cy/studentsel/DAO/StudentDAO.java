package com.cy.studentsel.DAO;



import com.cy.studentsel.entity.StudentRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author leaf-fulture
 * @date 2023/5/19 21:11
 */
@Mapper
@Repository(value = "StudentDAO")
public interface StudentDAO {
    public List<StudentRecord> queryAllStudent();

    public List<StudentRecord> queryStudentByCondition(StudentRecord record);

    public List<StudentRecord> queryStudentByPage(@Param("page") int page, @Param("size") int size);

    public int updateStudent(StudentRecord record);

    public int deleteStudent(@Param("student_id") String ID);

    public int addStudent(StudentRecord record);

    public StudentRecord queryStudentByID(String studentId);
}

