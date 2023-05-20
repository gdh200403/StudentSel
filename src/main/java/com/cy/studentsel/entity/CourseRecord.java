package com.cy.studentsel.entity;

import java.io.Serializable;
/**
 * @author leaf-fulture
 * @date 2023/4/24 19:14
 */
//public record CourseRecord(
//        String ID,
//        String Name,
//        int Credit,
//        String Type,
//        String Teacher,
//        String time,
//        String place,
//        int capacity
//) {
//}

public class CourseRecord implements Serializable{
    private String ID;
    private String Name;
    private int Credit;
    private String Type;
    private String Teacher;
    private String time;
    private String place;
    private int capacity;

    public CourseRecord(String ID, String name, int credit, String type, String teacher, String time, String place, int capacity) {
        this.ID = ID;
        Name = name;
        Credit = credit;
        Type = type;
        Teacher = teacher;
        this.time = time;
        this.place = place;
        this.capacity = capacity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCredit() {
        return Credit;
    }

    public void setCredit(int credit) {
        Credit = credit;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
