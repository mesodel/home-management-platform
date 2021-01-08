package com.unibuc.homemanagementplatform.model;

import java.util.Date;

public class User_Task {

    private Date createdOn;
    private User user;
    private Task task;

    public User_Task() {
    }

    public User_Task(Date createdOn, User user, Task task) {
        this.createdOn = createdOn;
        this.user = user;
        this.task = task;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}

