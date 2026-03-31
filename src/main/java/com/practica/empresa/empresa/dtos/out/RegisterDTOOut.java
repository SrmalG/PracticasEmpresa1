package com.practica.empresa.empresa.dtos.out;

import java.time.LocalDateTime;

public class RegisterDTOOut {

    private String message;
    private String userId;
    private LocalDateTime createdAt;

    public RegisterDTOOut(final String message, final String user, final LocalDateTime createdAt) {
        this.message = message;
        this.userId = user;
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "RegisterDTOOut{" +
                "message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
