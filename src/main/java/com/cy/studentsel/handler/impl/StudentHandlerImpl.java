package com.cy.studentsel.handler.impl;



import com.cy.studentsel.DAO.SCDAO;
import com.cy.studentsel.DAO.StudentDAO;
import com.cy.studentsel.entity.SCRecord;
import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.handler.StudentHandler;
import com.cy.studentsel.handler.ex.HandlerSqlException;
import com.cy.studentsel.handler.ex.PasswordNoMatchException;
import com.cy.studentsel.handler.ex.UserNameNoFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @author leaf-fulture
 * @date 2023/5/20 14:30
 */
@Service
public class StudentHandlerImpl implements StudentHandler {
    @Resource
    private StudentDAO studentDao;
    @Resource
    private SCDAO scDAO;

    @Override
    public String login(String ID, String pwd) {
        StudentRecord studentRecord = studentDao.queryStudentByID(ID);
        if (studentRecord == null) {
            throw new UserNameNoFoundException("学号不存在");
        }
        if (!Objects.equals(studentRecord.getPwd(), pwd)){
            throw new PasswordNoMatchException("密码错误");
        }
        return "登录成功";
    }




    @Override
    public StudentRecord getStudent(String ID) {
        return studentDao.queryStudentByID(ID);
    }

    @Override
    public boolean update(StudentRecord studentRecord) {
        studentDao.updateStudent(studentRecord);
        return true;
    }

    @Override
    public void addSC(SCRecord record) {

    }

    @Override
    public List<SCRecord> querySCByStudentId(String student_id) {
        return scDAO.querySCByStudentID(student_id);
    }



    public static void main(String[] args) {
        StudentHandlerImpl studentHandler = new StudentHandlerImpl();
        System.out.println(studentHandler.login("2019210001", "123456"));
    }
}
