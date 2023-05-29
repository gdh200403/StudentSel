package com.cy.studentsel.mapper;

import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.handler.AdminHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author leaf-fulture
 * @date 2023/5/25 17:56
 */
@SpringBootTest
public class TestAdminHanderImpl {
    @Autowired
    private AdminHandler adminHandler;

    @Test
    public void testQueryStudentByCondition() {
        StudentRecord record = new StudentRecord("", "", "", 20, "", "");
        System.out.println(adminHandler.queryStudentByCondition(record));
    }

    @Test
    public void testUpdateStudent() {
        StudentRecord record = new StudentRecord("20160001", "", "女", -1, "123", "");
        adminHandler.updateStudent(record);
    }
}
