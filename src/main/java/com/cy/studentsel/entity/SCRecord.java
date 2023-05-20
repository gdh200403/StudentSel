package com.cy.studentsel.entity;

import java.io.Serializable;

public class SCRecord implements Serializable {
    private String ID;
    private String CourseID;
    private int grade;

    public SCRecord(String ID, String courseID, int grade) {
        this.ID = ID;
        CourseID = courseID;
        this.grade = grade;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        CourseID = courseID;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
