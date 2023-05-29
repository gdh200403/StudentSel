package com.cy.studentsel.controler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leaf-fulture
 * @date 2023/5/21 20:45
 */

/**
 * Data transfer object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String userType;

}
