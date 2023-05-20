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
private String Major;
    private String PlanID;

    public CoursePlanRecord(String major, String planID) {
        Major = major;
        PlanID = planID;
    }

    public String getMajor() {
        return Major;
    }

    public String getPlanID() {
        return PlanID;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public void setPlanID(String planID) {
        PlanID = planID;
    }

    @Override
    public String toString() {
        return "CoursePlanRecord{" +
                "Major='" + Major + '\'' +
                ", PlanID='" + PlanID + '\'' +
                '}';
    }
}