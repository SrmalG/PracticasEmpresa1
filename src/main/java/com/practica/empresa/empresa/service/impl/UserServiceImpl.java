package com.practica.empresa.empresa.service.impl;


import com.practica.empresa.empresa.model.User;
import com.practica.empresa.empresa.security.SecurityUtilities;
import com.practica.empresa.empresa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practica.empresa.empresa.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Attempts to log in a user with the given credentials.
     *
     * <p>The method looks up a user by username. If no user is found,
     * the login attempt fails. If the user exists and the provided
     * password matches the stored password, the user's last login
     * timestamp is updated.</p>
     *
     * @param username the username of the user attempting to log in
     * @param password the password provided by the user
     * @return {@code false} if no user with the given username exists;
     *         {@code true} otherwise
     */
    public boolean login(final String username, final String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) return false;
        if(user.get().getPassword().equals(SecurityUtilities.sha256(password))) {
            userRepository.updateLastLogin(username, LocalDateTime.now());
            return true;
        }
        return false;
    }

    /**
     * Registers a new user in the system.
     *
     * <p>The method checks whether the username already exists and validates that
     * both the username and password are not null or blank. If any validation
     * fails, a descriptive message is returned. Otherwise, the new user is
     * successfully saved.</p>
     *
     * @param username the username to be registered
     * @param password the password associated with the user
     * @return a message indicating the registration result:
     *
     */
    public String register(final String username, final String password) {
        if (userRepository.findByUsername(username).isPresent())
            return "El usuario ya existe";

        if (username == null || username.isBlank() || password == null || password.isBlank())
            return "Usuario o contraseña no pueden estar vacíos";


        final User newUser = new User(username, SecurityUtilities.sha256(password));
        userRepository.save(newUser);
        return "Usuario registrado correctamente";
    }
    /**
     * Deletes a user by username.
     *
     * <p>The method first validates that the username is not null or blank.
     * If the username is invalid, it returns an appropriate error message.
     * Then it checks whether a user with the given username exists. If not,
     * it returns a message indicating that the user does not exist.
     * Otherwise, it deletes the user and returns a success message.</p>
     *
     * @param username the username of the user to be deleted
     * @return a message indicating the result of the operation:
     */
    public String deleteUser(final String username) {
        if (username == null || username.isBlank())
            return "El nombre de usuario no puede estar vacío";

        if (userRepository.findByUsername(username).isEmpty())
            return "El usuario no existe";

        userRepository.deleteUser(username);
        return "Usuario eliminado correctamente";
    }

}