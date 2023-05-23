package com.cy.studentsel.handler;

/**
 * @author leaf-fulture
 * @date 2023/5/22 15:14
 */
public interface UserHandler {
    /**
     * 登录
     * @param ID
     * @param pwd
     * @return
     */
    public String login(String ID, String pwd);
}
