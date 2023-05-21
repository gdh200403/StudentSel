package com.cy.studentsel.entity;

import java.io.Serializable;

public class TCRecord implements Serializable {
    private String id;
    private String courseid;
    private String capacity;

    public TCRecord(String id, String courseid, String capacity) {
        this.id = id;
        this.courseid = courseid;
        this.capacity = capacity;
    }

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

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "TCRecord{" +
                "id='" + id + '\'' +
                ", courseid='" + courseid + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
