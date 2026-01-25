package com.practica.empresa.empresa.service;

public interface UserService {

    boolean login(final String username, final String password);

    String register(final String username, final String password);

    String deleteUser(final String username);
}
