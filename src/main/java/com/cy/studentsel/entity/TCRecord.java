package com.cy.studentsel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TCRecord implements Serializable {
    private String teacher_id;
    private String course_id;
    private int current = -1;
    private int capacity = -1;
    private String term;
    private String place;
    private String comment = "";

    //course information
    private String course_name;
    private String type;
    private int credit;
    private int total_hours;
    private int teaching_hours;
    private int experiment_hours;
    private String teacher_name;

    public TCRecord(String teacher_id, String course_id, String teacher_name, String course_name, String type, int credit, String place) {
        this.teacher_id = teacher_id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.type = type;
        this.credit = credit;
        this.place = place;
        this.teacher_name = teacher_name;
    }

    public TCRecord(String id, String course_id, int current, int capacity, String comment) {
        this.teacher_id = id;
        this.course_id = course_id;
        this.current = current;
        this.capacity = capacity;
        this.comment = comment;
    }

    public TCRecord(String teacher_id, String course_id, String course_name, String type, int credit, String place, String teacher_name) {
        this.teacher_id = teacher_id;
        this.course_id = course_id;
        this.course_name = course_name;
        this.type = type;
        this.credit = credit;
        this.place = place;
        this.teacher_name = teacher_name;
    }
}
