package com.desafio.hotmart.exception;

public class ProductException extends Exception {
    public ProductException(String message) {
        super("Error on handle product: " + message);
    }
}
