package com.example.emp_regtrn.exception;



public class DepartmentAlreadyExistsException extends RuntimeException {

    public DepartmentAlreadyExistsException(String message) {
        super(message);
    }
}