package com.unibuc.homemanagementplatform.dto;

import javax.validation.constraints.NotNull;

public class UserRequestTaskCreate {
    @NotNull
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UserRequestTaskCreate() {

    }

    public UserRequestTaskCreate(@NotNull String userEmail) {
        this.userEmail = userEmail;


    }
}
