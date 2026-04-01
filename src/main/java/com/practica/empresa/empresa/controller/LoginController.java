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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody final LoginDTO loginDTO) {
        try {
            final String jwt = userService.login(loginDTO.getUsername(), loginDTO.getPassword());

            final LoginDTOOut response = new LoginDTOOut(
                    "Logged-in successfully",
                    loginDTO.getUsername(),
                    jwt
            );

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse(false, "Usuario o contraseña inválidas", null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody final RegisterDTO registerDto) {
            final boolean result = userService.register(
                    registerDto.getUsername(),
                    registerDto.getPassword(),
                    registerDto.getEmail()
            );

            if (result) {
                final RegisterDTOOut response = new RegisterDTOOut(
                        "User created successfully",
                        registerDto.getUsername(),
                        LocalDateTime.now()
                );
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                return ResponseEntity.badRequest()
                        .body(new RegisterDTOOut("User already exists", null, null));
            }

        }

    @DeleteMapping("/delete")
 //   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody final DeleteUserDTO request) {

            final boolean result = userService.deleteUser(request.getUsername());

            if (result) {
                return ResponseEntity.ok(new DeleteDTOOut("User deleted correctly"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new GenericResponse(false, "User not found", null));
            }
        }


}