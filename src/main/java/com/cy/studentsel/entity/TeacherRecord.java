package com.cy.studentsel.entity;
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

public class TeacherRecord {
    String id;
    String name;
    String sex;     //职务
    String age;   //学历
    String pwd;

    public TeacherRecord(String id, String name, String sex, String age, String pwd) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
