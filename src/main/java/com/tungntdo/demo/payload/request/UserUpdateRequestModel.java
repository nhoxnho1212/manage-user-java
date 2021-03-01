package com.tungntdo.demo.payload.request;

import com.tungntdo.demo.config.GlobalConfigs;

import javax.validation.constraints.Size;

public class UserUpdateRequestModel {

    @Size(
            min = GlobalConfigs.USER_REQUEST_VALIDATION.USER.MIN_NAME_LENGTH,
            max = GlobalConfigs.USER_REQUEST_VALIDATION.USER.MAX_NAME_LENGTH,
            message = GlobalConfigs.MESSAGES_VALIDATION.FIRST_NAME_AT_LEAST_1_CHARACTERS
    )
    private String firstName;

    @Size(
            min = GlobalConfigs.USER_REQUEST_VALIDATION.USER.MIN_NAME_LENGTH,
            max = GlobalConfigs.USER_REQUEST_VALIDATION.USER.MAX_NAME_LENGTH,
            message = GlobalConfigs.MESSAGES_VALIDATION.LAST_NAME_AT_LEAST_1_CHARACTERS
    )
    private String lastName;

    // ---- Getter, Setter ----

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
