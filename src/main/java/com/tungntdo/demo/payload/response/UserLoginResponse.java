package com.tungntdo.demo.payload.response;

public class UserLoginResponse {
    private String accessToken;

    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "{" +
                "\"accessToken\": \"" + accessToken + '\"' +
                ", \"refreshToken\": \"" + refreshToken + '\"' +
                '}';
    }
}
