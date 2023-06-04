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

    @Test
    void getConnection() throws SQLException {
        System.out.println(DataS.getConnection());
    }
}
