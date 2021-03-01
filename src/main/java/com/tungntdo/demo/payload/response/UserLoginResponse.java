package com.tungntdo.demo.payload.response;

public class UserLoginResponse {
    private String accessToken;

    private String refreshToken;

    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"accessToken\": \"" + accessToken + '\"' +
                ", \"refreshToken\": \"" + refreshToken + '\"' +
                ", \"userId\": \"" + userId + '\"' +
                '}';
    }
}
