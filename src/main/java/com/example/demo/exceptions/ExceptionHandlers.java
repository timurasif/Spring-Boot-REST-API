package com.example.demo.exceptions;

import com.example.demo.models.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Response> handleDateTimeParseException(DateTimeParseException ex) {
        String errorMessage = "Invalid date format. Please provide the date in the format yyyy-MM-dd.";
        Response response = new Response(400, errorMessage, null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFoundException(UserNotFoundException ex) {
        String errorMessage = ex.getMessage();
        Response response = new Response(404, errorMessage, null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Response> handleProductNotFoundException(ProductNotFoundException ex) {
        String errorMessage = ex.getMessage();
        Response response = new Response(404, errorMessage, null);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(WrongPriceException.class)
    public ResponseEntity<Response> handleWrongPriceException(WrongPriceException ex) {
        String errorMessage = ex.getMessage();
        Response response = new Response(404, errorMessage, null);
        return ResponseEntity.badRequest().body(response);
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class WrongPriceException extends RuntimeException {
        public WrongPriceException(String message) {
            super(message);
        }
    }
}
