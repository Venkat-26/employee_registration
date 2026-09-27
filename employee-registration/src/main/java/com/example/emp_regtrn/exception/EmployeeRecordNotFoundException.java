package com.example.emp_regtrn.exception;



public class EmployeeRecordNotFoundException extends RuntimeException {

    public EmployeeRecordNotFoundException(String message) {
        super(message);
    }
}
