package com.unibuc.homemanagementplatform.model;

import java.sql.Timestamp;

public class LogMessage {

    private int messageId;
    private Timestamp timestamp;
    private String message;

    public LogMessage(int messageId, Timestamp timestamp, String message) {
        this.messageId = messageId;
        this.timestamp = timestamp;
        this.message = message;
    }

    public LogMessage() {
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
