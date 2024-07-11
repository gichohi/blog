package com.dela.blog.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ArticleNotFoundException.class)
    public ResponseEntity<ApiError> articleNotFoundExceptionHandler(ArticleNotFoundException articleNotFoundException){
        ApiError apiError = new ApiError();
        apiError.setMessage("Article Not Found");
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> validationExceptionHandler(MethodArgumentNotValidException ex){
        ApiError apiError = new ApiError();
        apiError.setMessage("Bad Request");
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
        ApiError apiError = new ApiError();
        apiError.setMessage("Id variable should be a positive number");
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /*
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> exceptionHandler(Exception exception){
        ApiError apiError = new ApiError();
        apiError.setMessage("Unknown Error");
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

     */
}
