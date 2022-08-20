package com.tamara.app.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(
        OrderNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Order Not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<?> handleConstraintViolationExceptions(
        ConstraintViolationException ex) {
    	   String exceptionResponse = String.format("Invalid input parameters: %s\n", ex.getMessage());
    	     	 Map<String, Object> body = new LinkedHashMap<>();
         body.put("timestamp", LocalDateTime.now());
         body.put("message", exceptionResponse);
      return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
    
}