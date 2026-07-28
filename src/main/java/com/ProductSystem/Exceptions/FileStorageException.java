package com.ProductSystem.Exceptions;

import java.io.IOException;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message, IOException ex) {
        super(message);
    }

    public FileStorageException(String message) {
        super(message);
    }
}
