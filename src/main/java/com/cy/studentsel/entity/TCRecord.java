package com.cy.studentsel.entity;

import java.io.Serializable;

public class TCRecord implements Serializable {
    private String id;
    private String courseid;
    private int capacity = -1;

    @Override
    public String toString() {
        return "TCRecord{" +
                "id='" + id + '\'' +
                ", courseid='" + courseid + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public TCRecord(String id, String courseid, int capacity) {
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
