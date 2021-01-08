package com.unibuc.homemanagementplatform.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRequestCreate {

    @NotNull
    private String userEmail;
    private long familyId;
    private String name;
    @NotEmpty
    private String password;


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRequestCreate() {
    }

    public UserRequestCreate(@NotNull String userEmail, long familyId, String name, @NotEmpty String password) {
        this.userEmail = userEmail;
        this.familyId = familyId;
        this.name = name;
        this.password = password;
    }
}
