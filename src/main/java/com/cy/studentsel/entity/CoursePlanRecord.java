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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPlanid() {
        return planid;
    }

    public void setPlanid(String planid) {
        this.planid = planid;
    }

    public CoursePlanRecord(String major, String planid) {
        this.major = major;
        this.planid = planid;
    }
}