package com.mkyong.web.exception;

public class NameNotFoundException extends RuntimeException {
    public NameNotFoundException(String name) {
        super(String.format("Name not found: %s", name));
    }
}
