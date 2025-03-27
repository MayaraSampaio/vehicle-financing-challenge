package com.challenge.financing.Controller.Handlers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("errors", errors);

        return errorResponse;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", ex.getMessage());

        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleGeneralException(Exception ex) {
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("message", "Erro interno do servidor");
        errorResponse.put("details", ex.getMessage());

        return errorResponse;
    }
}