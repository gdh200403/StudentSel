package com.cy.studentsel.mapper;

import com.cy.studentsel.entity.TeacherRecord;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.handler.TeacherHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestTeacherHandlerlmpl {
    @Autowired
    private TeacherHandler teacherHandler;
    @Test
    public void testlogin(){System.out.println(teacherHandler.login("PB42000007","123456"));}
    @Test
    public void testgetTeacher(){
        System.out.println(teacherHandler.getTeacher("PB42000007"));}
    @Test
    public void testupdate(){
        TeacherRecord a=new TeacherRecord("PB42000007","faker","double",4,"123456");
        teacherHandler.update(a);}
}
