package com.cy.studentsel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRecord implements Serializable{
    private String course_id;
    private String course_name;
    private String type;
    private int credit;
    private int total_hours;
    private int teaching_hours;
    private int experiment_hours;

    public CourseRecord(String course_id, String course_name, String type, int credit) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.type = type;
        this.credit = credit;
    }
}
