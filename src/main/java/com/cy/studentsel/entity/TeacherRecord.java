package com.cy.studentsel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRecord {
    String teacher_id;
    String teacher_name;
    String sex;     //性别
    int age;   //年龄
    String pwd = "";

    public TeacherRecord(String teacher_id, String teacher_name, String sex, int age) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.sex = sex;
        this.age = age;
    }

}
