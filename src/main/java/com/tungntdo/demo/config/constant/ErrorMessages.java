package com.tungntdo.demo.config.constant;

public enum ErrorMessages {
    MISSING_REQUIRED_FIELD("Missing required field"),
    RECORD_ALREADY_ERROR("Record already error"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("No record found"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_CREATE_RECORD("Could not create record"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COUNT_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address not verified"),
    VALIDATION_ERROR("Validation error"),
    NO_DATABASE("No Database! Please install Mysql");


    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
