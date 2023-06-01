package com.cy.studentsel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;/**
 * @author leaf-fulture
 * @date 2023/4/24 17:38
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRecord implements Serializable {
    private String student_id;
    private String student_name;
    private String sex;
    private Integer age;
    private String major;
    private String pwd = "";


    public StudentRecord(String student_id, String student_name, String sex, Integer age, String major) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.sex = sex;
        this.age = age;
        this.major = major;
    }
}