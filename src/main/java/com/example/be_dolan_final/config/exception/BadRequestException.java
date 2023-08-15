package com.example.be_dolan_final.config.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(){
        super();
    }

}
