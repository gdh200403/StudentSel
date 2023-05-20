package com.cy.studentsel.entity;
import java.io.Serializable;

/**
 * @author leaf-fulture
 * @date 2023/4/24 18:18
 */
//public record CoursePlanRecord(
//        String Major,
//        String PlanID
//) {
//}
public class CoursePlanRecord implements Serializable{
private String major;
    private String planid;

    public CoursePlanRecord(String major, String planID) {
        this.major = major;
        planid = planID;
    }

    public String getMajor() {
        return major;
    }

    public String getPlanID() {
        return planid;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setPlanID(String planID) {
        planid = planID;
    }

    @Override
    public String toString() {
        return "CoursePlanRecord{" +
                "Major='" + major + '\'' +
                ", PlanID='" + planid + '\'' +
                '}';
    }
}