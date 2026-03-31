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
}
