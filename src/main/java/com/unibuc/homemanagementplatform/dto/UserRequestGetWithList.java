package com.unibuc.homemanagementplatform.dto;

import com.unibuc.homemanagementplatform.model.Task;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserRequestGetWithList {
    @NotNull
    private String userEmail;
    private String name;
    private String familyName;
    private List<Task> tasks;

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public UserRequestGetWithList() {
    }

    public UserRequestGetWithList(@NotNull String userEmail, String name, String familyName, List<Task> tasks) {
        this.userEmail = userEmail;
        this.name = name;
        this.familyName = familyName;
        this.tasks = tasks;
    }
}