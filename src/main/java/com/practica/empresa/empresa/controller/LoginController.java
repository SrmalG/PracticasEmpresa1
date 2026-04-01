package com.practica.empresa.empresa.controller;


import com.practica.empresa.empresa.dtos.GenericResponse;
import com.practica.empresa.empresa.dtos.in.DeleteUserDTO;
import com.practica.empresa.empresa.dtos.in.LoginDTO;
import com.practica.empresa.empresa.dtos.in.RegisterDTO;
import com.practica.empresa.empresa.dtos.out.DeleteDTOOut;
import com.practica.empresa.empresa.dtos.out.LoginDTOOut;
import com.practica.empresa.empresa.dtos.out.RegisterDTOOut;
import com.practica.empresa.empresa.service.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> login(@Valid @RequestBody final LoginDTO loginDTO) {

        try {
            boolean loggedIn = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
            return loggedIn
                    ? ResponseEntity.ok(new LoginDTOOut("Logged-in", loginDTO.getUsername() ))
                    : ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginDTOOut("User or password wrong", loginDTO.getUsername()));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new GenericResponse(false, e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody final RegisterDTO registerDto) {
        try {
            final boolean result = userService.register(
                    registerDto.getUsername(),
                    registerDto.getPassword(),
                    registerDto.getEmail()
            );

            return result
                    ? ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RegisterDTOOut("User created correctly", registerDto.getUsername(), LocalDateTime.now()))
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RegisterDTOOut("User already exists", null, null));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new GenericResponse(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody final DeleteUserDTO request) {
        try {
            final boolean result = userService.deleteUser(request.getUsername());
            return result
                    ? ResponseEntity.ok(new DeleteDTOOut("User deleted correctly"))
                    : ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new GenericResponse(false, "User not found", null));

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }






}
