package com.unibuc.homemanagementplatform.dto;

import com.unibuc.homemanagementplatform.model.Status;
import com.unibuc.homemanagementplatform.model.User;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class TaskRequestGet {

    @NotNull
    private Long taskId;
    private String name;
    private String description;
    private Status status;
    private Date dueBy;

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

    public TaskRequestGet() {
    }

    public TaskRequestGet(@NotNull Long taskId, String name, String description, Status status, Date dueBy) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.dueBy = dueBy;
    }
}
