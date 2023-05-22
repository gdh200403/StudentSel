package com.cy.studentsel.handler;

import com.cy.studentsel.entity.AdminRecord;

/**
 * @author leaf-fulture
 * @date 2023/5/20 21:54
 */
public interface AdminHandler {
    public String login(String ID, String pwd);

    public AdminRecord getAdmin(String ID);

    public boolean update(String ID, String pwd);
}
