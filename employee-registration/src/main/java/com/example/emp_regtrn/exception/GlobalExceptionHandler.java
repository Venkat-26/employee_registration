package com.example.emp_regtrn.exception;



import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Department already exists
    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleDepartmentAlreadyExists(
            DepartmentAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", ex.getMessage()));
    }

    // Department not found
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDepartmentNotFound(
            DepartmentNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    // Email already exists
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", ex.getMessage()));
    }

    // Employee age is less than 18
    @ExceptionHandler(InvalidEmployeeAgeException.class)
    public ResponseEntity<Map<String, String>> handleInvalidEmployeeAge(
            InvalidEmployeeAgeException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", ex.getMessage()));
    }

    // General IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(
            IllegalArgumentException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", ex.getMessage()));
    }
    @ExceptionHandler(EmployeeRecordNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeRecordNotFound(
            EmployeeRecordNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }
    
    @ExceptionHandler(PincodeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePincodeNotFound(
            PincodeNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }
}