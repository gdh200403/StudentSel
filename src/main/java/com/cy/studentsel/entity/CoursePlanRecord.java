package com.cy.studentsel.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author leaf-fulture
 * @date 2023/4/24 18:18
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePlanRecord implements Serializable{
    private String major;
    private String planid;

}