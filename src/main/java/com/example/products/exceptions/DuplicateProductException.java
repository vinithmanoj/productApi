package com.example.products.exceptions;

public class DuplicateProductException extends RuntimeException{

    public DuplicateProductException(String message) {
        super(message);
    }
}
