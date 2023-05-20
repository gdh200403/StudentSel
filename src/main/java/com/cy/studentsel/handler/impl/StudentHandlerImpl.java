package com.cy.studentsel.handler.impl;



import com.cy.studentsel.DAO.StudentDAO;
import com.cy.studentsel.entity.StudentRecord;
import com.cy.studentsel.handler.StudentHandler;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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

    @Override
    public String login(String ID, String pwd) {
        try {
            StudentRecord studentRecord = studentDao.queryStudentByID(ID);
            if (studentRecord == null) {
                return "student_not_exist";
            }
            if (Objects.equals(studentRecord.getPwd(), pwd)){
                return "login_success";
            }
            else {
                return "pwd_error";
            }
        }
        catch (Exception e){
            return "error";
        }
    }

    @Override
    public List<StudentRecord> queryAllStudent() {
        try {
            return studentDao.queryAllStudent();
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StudentRecord getStudent(String ID) {
        try {
            return studentDao.queryStudentByID(ID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(StudentRecord studentRecord) {
        try {
            studentDao.updateStudent(studentRecord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        StudentHandlerImpl studentHandler = new StudentHandlerImpl();
        System.out.println(studentHandler.login("2019210001", "123456"));
    }
}
