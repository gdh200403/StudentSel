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
    private String id;
    private String name;
    private String type;
    private int credit;
    private int total_hours;
    private int teaching_hours;
    private int experiment_hours;

    @Override
    public String toString() {
        return "CourseRecord{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", credit=" + credit +
                ", totalhours=" + total_hours +
                ", teachinghours=" + teaching_hours +
                ", experimenthours=" + experiment_hours +
                '}';
    }

}
