package br.com.alura.challenge_forum_hub.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException e){
        var error = e.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(ValidationErrorDetails::new).toList());
    }

    private record ValidationErrorDetails(String field, String message){
        public ValidationErrorDetails(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}