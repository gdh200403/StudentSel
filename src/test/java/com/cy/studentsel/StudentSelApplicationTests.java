package com.cy.studentsel;

import com.cy.studentsel.DAO.StudentDAO;
import com.cy.studentsel.entity.StudentRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StudentSelApplicationTests {
    @Autowired
    private DataSource DataS;
    @Qualifier("StudentDAO")
    @Autowired
    private StudentDAO student;

    @Test
    void contextLoads() {
    }

    @Test
    void getConnection() throws SQLException {
        System.out.println(DataS.getConnection());
//        System.out.println(student.getStudent("2019210001"));
//        System.out.println(student.login("2019210001","123456"));
        System.out.println(student.addStudent(new StudentRecord("1","张百嘉","男",20,"计算机科学与技术","123456")));
        System.out.println(student.queryAllStudent());
    }

}
