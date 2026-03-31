package com.practica.empresa.empresa.dtos.in;

public class DeleteUserDTO {

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
