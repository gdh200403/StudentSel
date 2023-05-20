package com.cy.studentsel.handler;

/**
 * @author leaf-fulture
 * @date 2023/5/20 17:51
 */
public interface TeacherHandler {
    public String login(String ID, String pwd);

    public String getTeacher(String ID);

    public boolean update(String ID, String pwd);
}
