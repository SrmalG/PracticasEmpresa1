package com.practica.empresa.empresa.dtos.out;

public class LoginDTOOut {

    private String message;
    private String user;

    public LoginDTOOut(final String message, final String user) {
        this.message = message;
        this.user = user;
    }
}
