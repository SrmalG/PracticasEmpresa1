package com.practica.empresa.empresa.service.impl;


import com.practica.empresa.empresa.model.User;
import com.practica.empresa.empresa.security.password.HashStrategy;
import com.practica.empresa.empresa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practica.empresa.empresa.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HashStrategy hashStrategy;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final HashStrategy hashStrategy) {
        this.userRepository = userRepository;
        this.hashStrategy = hashStrategy;
    }
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
    @Transactional
    public boolean login(final String username, final String password) {
        final Optional<User> userOpt = getUser(username);
        if (userOpt.isPresent() && hashStrategy.check(password, userOpt.get().getPassword())) {
            updateLastLogin(username);
            return true;
        }
        return false;
    }

    @Transactional
    public void updateLastLogin(final String username) {
        userRepository.updateLastLogin(username, LocalDateTime.now());
    }

    /**
     * Retrieves a user by their username.
     *
     * <p>This method performs read-only operations on the database,
     * therefore it is marked with {@code readOnly = true} to optimize
     * transaction performance.</p>
     *
     * @param username the username to search for (must not be {@code null} or blank)
     * @return the {@link User} associated with the given username
     */
    @Transactional(readOnly = true)
    public Optional<User> getUser(final String username) {
        return userRepository.findByUsername(username);
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
    @Transactional
    public boolean register(final String username, final String password, final String email) {
        if (getUser(username).isPresent())
            return false;

        final User newUser = new User(username, hashStrategy.hash(password), email);
        userRepository.save(newUser);
        return true;
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
    @Transactional
    public boolean deleteUser(final String username) {
        if (getUser(username).isEmpty())
            return false;

        final int updatedRows = userRepository.deleteUser(username);
        return updatedRows != 0;
    }

}