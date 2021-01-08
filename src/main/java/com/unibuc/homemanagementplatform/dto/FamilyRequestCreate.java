package com.unibuc.homemanagementplatform.dto;

import javax.validation.constraints.NotNull;

public class FamilyRequestCreate {

    @NotNull
    private String familyName;

    public FamilyRequestCreate() {
    }

    public FamilyRequestCreate(@NotNull String familyName) {
        this.familyName = familyName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
