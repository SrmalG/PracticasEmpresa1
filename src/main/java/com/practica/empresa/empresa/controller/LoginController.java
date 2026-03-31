package com.practica.empresa.empresa.controller;


import com.practica.empresa.empresa.dtos.DeleteUserDTO;
import com.practica.empresa.empresa.dtos.LoginDTO;
import com.practica.empresa.empresa.dtos.RegisterDTO;
import com.practica.empresa.empresa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class LoginController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody final LoginDTO registerDto) {

        return userService.login(registerDto.getUsername(),
                registerDto.getPassword()) ?
                ResponseEntity.ok("✅ Login correcto") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody final RegisterDTO registerDto) {
        final boolean result = userService.register(
                registerDto.getUsername(),
                registerDto.getPassword()
        );

        if (result) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Usuario registrado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar usuario");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody final DeleteUserDTO request) {

        final boolean result = userService.deleteUser(request.getUsername());

        if (result) {
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se pudo eliminar el usuario");
        }
    }






}
