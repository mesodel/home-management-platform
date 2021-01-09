package com.unibuc.homemanagementplatform.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unibuc.homemanagementplatform.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class TaskRequestCreate {

    private String name;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dueBy;

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

    public LocalDateTime getDueBy() {
        return dueBy;
    }

    public void setDueBy(LocalDateTime dueBy) {
        this.dueBy = dueBy;
    }

    public List<UserRequestTaskCreate> getAssignedTo() {
        return users;
    }

    public void setUsers(List<UserRequestTaskCreate> users) {
        this.users = users;
    }
}
