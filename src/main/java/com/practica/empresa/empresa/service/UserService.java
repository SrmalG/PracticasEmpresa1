package com.practica.empresa.empresa.service;

import com.practica.empresa.empresa.model.User;

import java.util.Optional;

public interface UserService {

    String login(final String username, final String password);

    boolean register(final String username, final String password, final String email, final String rol);

    boolean deleteUser(final String username);

    Optional<User> getUser(final String username);
}
