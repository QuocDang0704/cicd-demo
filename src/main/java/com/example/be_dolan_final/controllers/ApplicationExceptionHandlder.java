package com.example.be_dolan_final.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.be_dolan_final.controllers.exception.ErrorMessager;

@ControllerAdvice
public class ApplicationExceptionHandlder extends ResponseEntityExceptionHandler {
    @ExceptionHandler(
            value = IllegalArgumentException.class
    )
    public ResponseEntity<ErrorMessager> handleNotFoundException(
            Exception exception
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessager.builder()
                        .code("INTERNAL_SERVER_ERROR")
                        .messager(exception.getMessage())
                        .build()
                );
    }
}
