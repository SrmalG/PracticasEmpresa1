package com.practica.empresa.empresa.controller;


import com.practica.empresa.empresa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.practica.empresa.empresa.service.impl.UserServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody final Map<String, String> body) {
        final String username = body.get("username");
        final String password = body.get("password");

        return userService.login(username, password) ?
                ResponseEntity.ok("✅ Login correcto") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody final Map<String, String> body) {
        final String username = body.get("username");
        final String password = body.get("password");
        final String result = userService.register(username, password);

        return switch (result) {
            case "El usuario ya existe" -> ResponseEntity.status(HttpStatus.CONFLICT).body(result);
            case "Usuario o contraseña no pueden estar vacíos" ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            default -> ResponseEntity.status(HttpStatus.CREATED).body(result);
        };
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody final Map<String, String> body) {
        final String username = body.get("username");
        final String result = userService.deleteUser(username);

        return switch (result) {
            case "El nombre de usuario no puede estar vacío",
                 "El usuario no existe" -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(result);
            case "Usuario eliminado correctamente" -> ResponseEntity
                    .status(HttpStatus.OK)
                    .body(result);
            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error desconocido");
        };
    }





}
