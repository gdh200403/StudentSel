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
    String name;
    String id;
    String sex;
    String job;     //职务
    String education;   //学历
    String major;
    String tel;

    @Override
    public String toString() {
        return "TeacherRecord{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sex='" + sex + '\'' +
                ", job='" + job + '\'' +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", tel='" + tel + '\'' +
                '}';
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

    public void setJob(String job) {
        this.job = job;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    public String getJob() {
        return job;
    }

    public String getEducation() {
        return education;
    }

    public String getMajor() {
        return major;
    }

    public String getTel() {
        return tel;
    }
}
