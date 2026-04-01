package com.practica.empresa.empresa.exception;

import com.practica.empresa.empresa.dtos.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        final String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        return ResponseEntity.badRequest().body(new GenericResponse(false, message, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleOtherExceptions(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(new GenericResponse(false, ex.getMessage(), null));
    }
}