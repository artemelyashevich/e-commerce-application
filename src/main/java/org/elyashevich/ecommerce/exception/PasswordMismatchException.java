package org.elyashevich.ecommerce.exception;

public class PasswordMismatchException extends RuntimeException{

    public PasswordMismatchException() {
    }

    public PasswordMismatchException(String message) {
        super(message);
    }
}
