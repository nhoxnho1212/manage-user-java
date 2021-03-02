package com.tungntdo.demo.payload.request;


import com.tungntdo.demo.config.GlobalConfigs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailRequestModel {

    @NotBlank(message = GlobalConfigs.MESSAGES_VALIDATION.FIRST_NAME_AT_LEAST_1_CHARACTERS)
    @Size(
            min = GlobalConfigs.USER_REQUEST_VALIDATION.USER.MIN_NAME_LENGTH,
            message = GlobalConfigs.MESSAGES_VALIDATION.FIRST_NAME_AT_LEAST_1_CHARACTERS
    )
    private String firstName;

    @NotBlank(message = GlobalConfigs.MESSAGES_VALIDATION.LAST_NAME_AT_LEAST_1_CHARACTERS)
    @Size(
            min = GlobalConfigs.USER_REQUEST_VALIDATION.USER.MIN_NAME_LENGTH,
            message = GlobalConfigs.MESSAGES_VALIDATION.LAST_NAME_AT_LEAST_1_CHARACTERS
    )
    private String lastName;

    @NotNull(message = GlobalConfigs.MESSAGES_VALIDATION.EMAIL_NOT_VALID)
    @Email(message = GlobalConfigs.MESSAGES_VALIDATION.EMAIL_NOT_VALID)
    private String email;

    @NotNull
    @Size(
            min = GlobalConfigs.USER_REQUEST_VALIDATION.USER.MIN_PASSWORD_LENGTH,
            message = GlobalConfigs.MESSAGES_VALIDATION.PASSWORD_AT_LEAST_6_CHARACTERS
    )
    private String password;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
