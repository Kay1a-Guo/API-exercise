package com.oocl.training.advice;

import com.oocl.training.exception.InvalidEmployeeException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(InvalidEmployeeException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidEmployeeException(InvalidEmployeeException ex) {
        Map<String, Object> errorBody = Map.of(
                "error", ex.getMessage(),
                "message", "Invalid employee request",
                "timestamp", System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }
}

