package com.cy.studentsel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SCRecord implements Serializable {
    private String student_id;
    private String course_id;
    private int grade;

    private String student_name;
    private String course_name;

    private String teacher_id;
    private String teacher_name;

    //course information
    private String type;
    private int credit;
    private int total_hours;
    private int teaching_hours;
    private int experiment_hours;
    private int current = -1;
    private int capacity = -1;
    private String term;
    private String place;
    private String comment = "";

    public SCRecord(String student_id, String course_id, int grade) {
        this.student_id = student_id;
        this.course_id = course_id;
        this.grade = grade;
    }

    public SCRecord(String student_id,  String course_id, String teacher_id, String student_name,
                    String course_name, String teacher_name,String type, int credit, String place) {
        this.student_id = student_id;
        this.teacher_id = teacher_id;
        this.course_id = course_id;
        this.student_name = student_name;
        this.course_name = course_name;
        this.type = type;
        this.credit = credit;
        this.place = place;
        this.teacher_name = teacher_name;
    }


}
