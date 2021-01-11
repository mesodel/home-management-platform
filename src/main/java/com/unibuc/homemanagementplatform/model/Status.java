package com.unibuc.homemanagementplatform.model;

public class Status {

    private long statusId;
    private StatusValue statusValue;

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusValue=" + statusValue +
                '}';
    }

    public Status(long statusId, StatusValue statusValue) {
        this.statusId = statusId;
        this.statusValue = statusValue;
    }


    public Status() {
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public StatusValue getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(StatusValue statusValue) {
        this.statusValue = statusValue;
    }
}
