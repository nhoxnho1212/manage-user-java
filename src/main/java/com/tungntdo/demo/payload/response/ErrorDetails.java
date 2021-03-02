package com.tungntdo.demo.payload.response;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private Boolean success;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, Boolean success, String message, String details) {
        this.timestamp = timestamp;
        this.success = success;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
