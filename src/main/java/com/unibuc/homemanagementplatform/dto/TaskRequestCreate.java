package com.unibuc.homemanagementplatform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class TaskRequestCreate {

    private String name;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dueBy;

    private List<UserRequestTaskCreate> users;

    public TaskRequestCreate() {
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

    public Date getDueBy() {
        return dueBy;
    }

    public void setDueBy(Date dueBy) {
        this.dueBy = dueBy;
    }

    public List<UserRequestTaskCreate> getAssignedTo() {
        return users;
    }

    public void setAssignedTo(List<UserRequestTaskCreate> users) {
        this.users = users;
    }

    public void setUsers(List<UserRequestTaskCreate> users) {
        this.users = users;
    }
}
