package com.practica.empresa.empresa.service;


import com.practica.empresa.empresa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practica.empresa.empresa.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean login(String username, String password) {
        return userRepository.findByUsername(username)
                .map(u -> u.getPassword().equals(password))
                .orElse(false);
    }

    public String register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return "⚠️ El usuario ya existe";
        }

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return "❌ Usuario o contraseña no pueden estar vacíos";
        }

        User newUser = new User(username, password);
        userRepository.save(newUser);
        return "✅ Usuario registrado correctamente";
    }
}