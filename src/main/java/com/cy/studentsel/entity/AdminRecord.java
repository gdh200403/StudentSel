package com.cy.studentsel.entity;

public class AdminRecord {
    private String id;
    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public AdminRecord(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
}
