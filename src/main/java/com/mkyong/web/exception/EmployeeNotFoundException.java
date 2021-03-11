package com.mkyong.web.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(long id) {
        super(String.format("Employee not found with Id: %d", id));
    }
}
