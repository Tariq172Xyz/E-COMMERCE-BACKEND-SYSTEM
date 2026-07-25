package com.ProductSystem.Exceptions;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String message){
        super(message);
    }
}
