package com.practica.empresa.empresa.controller;


import com.practica.empresa.empresa.dtos.in.DeleteUserDTO;
import com.practica.empresa.empresa.dtos.in.LoginDTO;
import com.practica.empresa.empresa.dtos.in.RegisterDTO;
import com.practica.empresa.empresa.dtos.out.DeleteDTOOut;
import com.practica.empresa.empresa.dtos.out.LoginDTOOut;
import com.practica.empresa.empresa.dtos.out.RegisterDTOOut;
import com.practica.empresa.empresa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class LoginController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final LoginDTO registerDto) {

        try {
            boolean loggedIn = userService.login(registerDto.getUsername(), registerDto.getPassword());
            return loggedIn
                    ? ResponseEntity.ok(new LoginDTOOut("Loggedin", registerDto.getUsername() ))
                    : ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario o contraseña incorrectos");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody final RegisterDTO registerDto) {
        try {
            final boolean result = userService.register(
                    registerDto.getUsername(),
                    registerDto.getPassword()
            );

            return result
                    ? ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RegisterDTOOut("User created correctly", registerDto.getUsername(), LocalDateTime.now()))
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar usuario");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody final DeleteUserDTO request) {
        try {
            final boolean result = userService.deleteUser(request.getUsername());
            return result
                    ? ResponseEntity.ok(new DeleteDTOOut("User deleted correctly"))
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se pudo eliminar el usuario");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }






}
