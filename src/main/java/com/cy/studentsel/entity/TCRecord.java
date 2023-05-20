package com.cy.studentsel.entity;

import java.io.Serializable;

public class TCRecord implements Serializable {
    private String id;
    private String Courseid;
    private String Capacity;

    public TCRecord(String id, String courseid, String capacity) {
        this.id = id;
        Courseid = courseid;
        Capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseid() {
        return Courseid;
    }

    public void setCourseid(String courseid) {
        Courseid = courseid;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }
}
