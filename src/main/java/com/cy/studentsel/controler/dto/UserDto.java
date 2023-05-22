package com.cy.studentsel.controler.dto;

import lombok.Data;

/**
 * @author leaf-fulture
 * @date 2023/5/21 20:45
 */

/**
 * Data transfer object
 */
@Data
public class UserDto {
    private String username;
    private String password;
    private String userType;

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
