package com.unibuc.homemanagementplatform.model;

public enum StatusValue {
    ASSIGNED("ASSIGNED"), FINISHED("FINISHED"), IN_PROGRESS("IN_PROGRESS");

    private String value;

    StatusValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
