package com.cy.studentsel.entity;

import java.io.Serializable;/**
 * @author leaf-fulture
 * @date 2023/4/24 17:38
 */

//public record StudentRecord(
//        String ID,
//        String Name,
//        String Sex,
//        int age,
//        String Major,
//        String pwd
//){}

public class StudentRecord implements Serializable {
    private String id;
    private String name;
    private String sex;
    private Integer age;
    private String major;
    private String pwd = "";

    public StudentRecord(){}

    public StudentRecord(String id, String name, String sex, Integer age, String major, String pwd){
        this.id = id;
        this.name = name;

        this.sex = sex;
        this.age = age;
        this.major = major;
        this.pwd = pwd;
    }

    public StudentRecord(String id, String name, String sex, Integer age, String major) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public String getPwd() {
        return pwd;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "StudentRecord{" +
                "ID='" + id + '\'' +
                ", Name='" + name + '\'' +
                ", Sex='" + sex + '\'' +
                ", age=" + age +
                ", Major='" + major + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}