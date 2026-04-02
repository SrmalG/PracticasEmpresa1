package com.practica.empresa.empresa.dtos.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterDTO {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, message = "El usuario debe tener al menos 4 caracteres")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 8 characters")
    private String password;

    @Email(message = "Invalid email")
    private String email;

    @Pattern(regexp = "^(USER|ADMIN)$",
            message = "El rol solo puede ser USER o ADMIN")
    private String rol;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public RegisterDTO() {}

    public RegisterDTO(final String username, final String password, final String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() { return email; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
