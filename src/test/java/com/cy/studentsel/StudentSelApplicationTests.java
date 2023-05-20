package com.cy.studentsel;

import com.cy.studentsel.DAO.StudentDao;
import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.handler.StudentHandler;
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
    @Qualifier("StudentDao")
    @Autowired
    private StudentDao student;

    @Test
    void contextLoads() {
    }

    @Test
    void getConnection() throws SQLException {
        System.out.println(DataS.getConnection());
//        System.out.println(student.getStudent("2019210001"));
//        System.out.println(student.login("2019210001","123456"));
//        System.out.println(student.addStudent(new StudentRecord("PB22000001","张百嘉","男",20,"计算机科学与技术","123456")));
        System.out.println(student.queryAllStudent());
    }

}
