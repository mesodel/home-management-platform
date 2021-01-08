package com.unibuc.homemanagementplatform.model;
import java.util.ArrayList;
import java.util.List;


public class User {

    private String userEmail;
    private Family family;
    private String name;
    private String password;
    private List<User_Task> tasks;

    public User(String userEmail, Family family, String name, String password, List<User_Task> tasks) {
        this.userEmail = userEmail;
        this.family = family;
        this.name = name;
        this.password = password;
        this.tasks = tasks;
    }

    public User(String userEmail, String name, String password) {
        this.userEmail = userEmail;
        this.name = name;
        this.password = password;
    }

    public User() {
        tasks = new ArrayList<>();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User_Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<User_Task> tasks) {
        this.tasks = tasks;
    }
}