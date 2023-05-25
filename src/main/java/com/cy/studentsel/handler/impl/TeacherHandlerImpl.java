package com.cy.studentsel.handler.impl;
import  com.cy.studentsel.DAO.TeacherDAO;
import com.cy.studentsel.entity.TeacherRecord;
import com.cy.studentsel.handler.TeacherHandler;
import com.cy.studentsel.handler.ex.HandlerException;
import com.cy.studentsel.handler.ex.HandlerSqlException;
import com.cy.studentsel.handler.ex.PasswordNoMatchException;
import com.cy.studentsel.handler.ex.UserNameNoFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author leaf-fulture
 * @date 2023/5/20 20:21
 */
@Service
public class TeacherHandlerImpl implements TeacherHandler {
    @Resource
    private TeacherDAO teacherDao;

    @Override
    public String login(String ID, String pwd) {
        TeacherRecord teacherRecord = teacherDao.queryTeacherByID(ID);
        if (teacherRecord == null) {
            throw new UserNameNoFoundException("教师工号不存在");
        }
        if (!Objects.equals(teacherRecord.getPwd(), pwd)){
            throw new PasswordNoMatchException("密码错误");
        }
        return "登录成功";
    }


    @Override
    public TeacherRecord getTeacher(String ID) {
        try {
            return teacherDao.queryTeacherByID(ID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(TeacherRecord teacherRecord) {
        try {
            teacherDao.updateTeacher(teacherRecord);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        TeacherHandlerImpl teacherHandler = new TeacherHandlerImpl();
        System.out.println(teacherHandler.login("2019210001", "123456"));
    }
}
