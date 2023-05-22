package com.cy.studentsel.handler.impl;

import com.cy.studentsel.DAO.AdminDAO;
import com.cy.studentsel.entity.AdminRecord;
import com.cy.studentsel.handler.AdminHandler;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
        try {
            AdminRecord admin = adminDAO.queryAdminByID(ID);
            if (admin == null) {
                return "admin_not_exist";
            }
            if (admin.getPwd().equals(pwd)){
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
