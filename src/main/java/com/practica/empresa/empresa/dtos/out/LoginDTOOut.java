package com.practica.empresa.empresa.dtos.out;

public class LoginDTOOut {

    private String message;
    private String user;

    public LoginDTOOut(final String message, final String user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginDTOOut{" +
                "message='" + message + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
