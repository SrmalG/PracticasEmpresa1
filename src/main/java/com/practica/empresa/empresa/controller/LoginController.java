package com.practica.empresa.empresa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.practica.empresa.empresa.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        final String username = body.get("username");
        final String password = body.get("password");

        return userService.login(username, password) ?
                ResponseEntity.ok("✅ Login correcto") :
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("❌ Usuario o contraseña incorrectos");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> body) {
        final String username = body.get("username");
        final String password = body.get("password");
        final String result = userService.register(username, password);

        return switch (result) {
            case "⚠️ El usuario ya existe" -> ResponseEntity.status(HttpStatus.CONFLICT).body(result); // 409
            case "❌ Usuario o contraseña no pueden estar vacíos" ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result); // 400
            default -> ResponseEntity.status(HttpStatus.CREATED).body(result); // 201
        };
    }


}
