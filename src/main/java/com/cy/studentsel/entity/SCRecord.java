package com.cy.studentsel.entity;

import java.io.Serializable;

public class SCRecord implements Serializable {
    private String id;
    private String courseid;
    private int grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public SCRecord(String id, String courseid, int grade) {
        this.id = id;
        this.courseid = courseid;
        this.grade = grade;
    }
}
