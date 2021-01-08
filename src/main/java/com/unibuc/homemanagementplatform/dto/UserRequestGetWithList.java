package com.unibuc.homemanagementplatform.dto;

import com.unibuc.homemanagementplatform.model.Task;
import com.unibuc.homemanagementplatform.model.User_Task;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserRequestGetWithList {
    @NotNull
    private String userEmail;
    private String name;
    private String familyName;
    private List<User_Task> tasks;

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

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public List<User_Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<User_Task> tasks) {
        this.tasks = tasks;
    }

    public UserRequestGetWithList() {
    }

    public UserRequestGetWithList(@NotNull String userEmail, String name, String familyName, List<User_Task> tasks) {
        this.userEmail = userEmail;
        this.name = name;
        this.familyName = familyName;
        this.tasks = tasks;
    }
}
