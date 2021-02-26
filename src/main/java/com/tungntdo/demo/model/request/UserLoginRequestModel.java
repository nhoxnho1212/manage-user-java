package com.tungntdo.demo.model.request;

import com.tungntdo.demo.config.GlobalConfigs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginRequestModel {
//    @Email(message = GlobalConfigs.MESSAGES_VALIDATION.EMAIL_NOT_VALID)
    private String email;

//    @NotNull
//    @Size(
//            min = GlobalConfigs.USER_REQUEST_VALIDATION.USER.MIN_PASSWORD_LENGTH,
//            message = GlobalConfigs.MESSAGES_VALIDATION.PASSWORD_AT_LEAST_6_CHARACTERS
//    )
    private String password;

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
