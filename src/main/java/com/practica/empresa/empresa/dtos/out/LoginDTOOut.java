package com.practica.empresa.empresa.dtos.out;

public class LoginDTOOut {

    private String message;
    private String user;
    private String jwt;

    public LoginDTOOut(final String message, final String user, final String jwt) {
        this.message = message;
        this.user = user;
        this.jwt = jwt;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "LoginDTOOut{" +
                "message='" + message + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
