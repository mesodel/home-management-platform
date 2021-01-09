package com.unibuc.homemanagementplatform.model;

import com.unibuc.homemanagementplatform.dto.UserRequestTaskCreate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {

    private Long taskId;
    private String name;
    private String description;
    private Status status;
    private Date dueBy;
    private List<UserRequestTaskCreate> users;

    public Task() {
        users = new ArrayList<>();
    }

    public Task(Long taskId, String name, String description, Status status, Date dueBy, List<UserRequestTaskCreate> users) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueBy = dueBy;
        this.users = users;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDueBy() {
        return dueBy;
    }

    public void setDueBy(Date dueBy) {
        this.dueBy = dueBy;
    }

    public List<UserRequestTaskCreate> getUsers() {
        return users;
    }

    public void setUsers(List<UserRequestTaskCreate> users) {
        this.users = users;
    }
}
