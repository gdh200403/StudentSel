package com.cy.studentsel.entity;

public class TeacherRecord {
    String id;
    String name;
    String sex;
    int age;
    String major;
    String pwd;

    @Override
    public String toString() {
        return "TeacherRecord{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public String getPwd() {
        return pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

}
