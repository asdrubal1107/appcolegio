package com.api.iberoamericana.colegio.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException exception){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(exception.getStatus().value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }

}
