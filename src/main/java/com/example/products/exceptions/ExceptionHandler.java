package com.example.products.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Set;
import java.util.StringJoiner;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(DuplicateProductException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleDuplicateProductException(DuplicateProductException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> handleProductNotFound(ProductNotFoundException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleBadRequest(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        StringJoiner sj = new StringJoiner(",");
        constraintViolations.forEach(violation-> sj.add(violation.getPropertyPath()+"-"+ violation.getMessage()));
        return new ResponseEntity(sj.toString(), HttpStatus.BAD_REQUEST);
    }
}
