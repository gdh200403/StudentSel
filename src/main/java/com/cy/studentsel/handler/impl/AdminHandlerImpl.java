package com.cy.studentsel.handler.impl;

import com.cy.studentsel.DAO.AdminDAO;
import com.cy.studentsel.entity.AdminRecord;
import com.cy.studentsel.handler.AdminHandler;
import com.cy.studentsel.handler.ex.HandlerSqlException;
import com.cy.studentsel.handler.ex.PasswordNoMatchException;
import com.cy.studentsel.handler.ex.UserNameNoFoundException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author leaf-fulture
 * @date 2023/5/20 21:55
 */
@Service
public class AdminHandlerImpl implements AdminHandler {
    @Resource
    private AdminDAO adminDAO;

    @Override
    public String login(String ID, String pwd) {
        AdminRecord admin = adminDAO.queryAdminByID(ID);
        if (admin == null) {
            throw new UserNameNoFoundException("管理员账号不存在");
        }
        if (!Objects.equals(admin.getPwd(), pwd)){
            throw new PasswordNoMatchException("密码错误");
        }
        return "登录成功";
    }

    @Override
    public AdminRecord getAdmin(String ID) {
        try {
            return adminDAO.queryAdminByID(ID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(String ID, String pwd) {
        try {
            adminDAO.updateAdmin(new AdminRecord(ID, pwd));
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
