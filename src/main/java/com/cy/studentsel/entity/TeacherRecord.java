package com.cy.studentsel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//
//public record TeacherRecord(
//        String Name,
//        String TeacherID,
//        String Sex,
//        String job,     //职务
//        String Education,   //学历
//        String Major,
//        String Tel
//) {
//}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRecord {
    String teacher_id;
    String teacher_name;
    String sex;     //职务
    int age;   //学历
    String pwd = "";

    public TeacherRecord(String teacher_id, String teacher_name, String sex, int age) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.sex = sex;
        this.age = age;
    }

}
