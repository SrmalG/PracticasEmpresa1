package com.practica.empresa.empresa.service;

public interface UserService {

    boolean login(final String username, final String password);

    boolean register(final String username, final String password, final String email);

    boolean deleteUser(final String username);
}
