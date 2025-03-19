package com.project.questapp.dtos.responses;

public class AuthResponse {
    String message;
    Long userId;

    public AuthResponse() {

    }

    public String getMessage() {
        return message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
