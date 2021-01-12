package com.unibuc.homemanagementplatform.dto;


import com.unibuc.homemanagementplatform.model.Task;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserRequestGet {

    @NotNull
    private String userEmail;
    private String name;
    private String familyName;
    private List<TaskRequestGet> tasks;

    public UserRequestGet() {
    }

    public List<TaskRequestGet> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskRequestGet> tasks) {
        this.tasks = tasks;
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

