package com.unibuc.homemanagementplatform.model;
import java.util.ArrayList;
import java.util.List;

public class Family {

    private Long familyId;
    private String familyName;
    private List<User> familyMembers;

    public Family() {
        familyMembers = new ArrayList<>();
    }

    public Family(Long familyId, String familyName, List<User> familyMembers) {
        this.familyId = familyId;
        this.familyName = familyName;
        this.familyMembers = familyMembers;
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

    public List<User> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<User> familyMembers) {
        this.familyMembers = familyMembers;
    }
}
