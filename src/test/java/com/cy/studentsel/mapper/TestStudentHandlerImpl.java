package com.cy.studentsel.mapper;

import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.handler.StudentHandler;
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
    public void testqueryALLStudent(){
        System.out.println(studentHandler.queryAllStudent());
    }
    @Test
    public void testlogin(){System.out.println(studentHandler.login("PB42000001","123456"));}
    @Test
    public void testgetstudent(){System.out.println(studentHandler.getStudent("PB42000001"));}
    @Test
    public void testupdate(){
        StudentRecord a=new StudentRecord("PB42000001","llf","male",21,"cs","123456");
        System.out.println(studentHandler.update(a));
    }
}
