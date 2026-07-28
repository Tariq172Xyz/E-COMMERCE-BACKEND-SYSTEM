package com.ProductSystem.Exceptions;

public class FileInvalidException extends RuntimeException {
    public FileInvalidException(String message) {
        super(message);
    }
}
