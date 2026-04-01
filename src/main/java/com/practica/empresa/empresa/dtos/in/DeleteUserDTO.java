package com.practica.empresa.empresa.dtos.in;

import jakarta.validation.constraints.NotBlank;

public class DeleteUserDTO {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    public DeleteUserDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DeleteUserDTO{" +
                "username='" + username + '\'' +
                '}';
    }
}
