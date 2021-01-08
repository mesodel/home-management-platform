package com.unibuc.homemanagementplatform.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FamilyRequestGet {

    @NotNull
    private Long familyId;

    @NotEmpty
    private String familyName;

    private List<UserRequestGet> users;

    public FamilyRequestGet() {
    }

    public Long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Long familyId) {
        this.familyId = familyId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public List<UserRequestGet> getUsers() {
        return users;
    }

    public void setUsers(List<UserRequestGet> users) {
        this.users = users;
    }
}
