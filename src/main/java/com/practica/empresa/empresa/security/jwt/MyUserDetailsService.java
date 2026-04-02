package com.practica.empresa.empresa.security.jwt;

import com.practica.empresa.empresa.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Cacheable(value = "userDetails", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userService.getUser(username)
                .map(user -> {
                    // Obtenemos el rol único del usuario (asumiendo que tienes un getter getRole())
                    String role = user.getRol();   // Ej: "ADMIN" o "USER"
                    return org.springframework.security.core.userdetails.User
                            .withUsername(user.getUsername())
                            .password(user.getPassword())
                            .roles(role.toUpperCase())     // Spring añade automáticamente "ROLE_"
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }
}
