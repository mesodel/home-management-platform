package com.unibuc.homemanagementplatform.dto;


import javax.validation.constraints.NotNull;

public class UserRequestGet {

    @NotNull
    private String userEmail;
    private String name;
    private String familyName;

    public UserRequestGet() {
    }

    public UserRequestGet(String userEmail, String name, String familyName) {
        this.userEmail = userEmail;
        this.name = name;
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

