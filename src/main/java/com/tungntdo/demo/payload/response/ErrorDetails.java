package com.tungntdo.demo.payload.response;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private Boolean success;
    private String message;
    private Object details;

    public ErrorDetails(Date timestamp, Boolean success, String message, Object details) {
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

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
