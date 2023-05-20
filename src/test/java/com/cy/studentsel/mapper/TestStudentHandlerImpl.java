package com.cy.studentsel.mapper;

import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.handler.impl.StudentHandlerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author leaf-fulture
 * @date 2023/5/20 20:43
 */
@SpringBootTest
public class TestStudentHandlerImpl {
    @Autowired
    private StudentHandler studentHandler;
    @Test
    public void testLogin(){
        System.out.println(studentHandler.queryAllStudent());
    }
}
