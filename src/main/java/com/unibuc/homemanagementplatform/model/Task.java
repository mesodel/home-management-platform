package com.unibuc.homemanagementplatform.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {

    private Long taskId;
    private String name;
    private String description;
    private User user;
    private Status status;
    private Date dueBy;
    private List<User_Task> users;

    public Task(Long taskId, String name, String description, User user, Status status, Date dueBy, List<User_Task> users) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.user = user;
        this.status = status;
        this.dueBy = dueBy;
        this.users = users;
    }

    public Task() {
        users = new ArrayList<>();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<User_Task> getUsers() {
        return users;
    }

    public void setUsers(List<User_Task> users) {
        this.users = users;
    }
}
