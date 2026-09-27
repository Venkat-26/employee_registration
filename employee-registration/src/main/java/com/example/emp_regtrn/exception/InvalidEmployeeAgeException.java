package com.example.emp_regtrn.exception;


public class InvalidEmployeeAgeException extends RuntimeException {

    public InvalidEmployeeAgeException(String message) {
        super(message);
    }
}
