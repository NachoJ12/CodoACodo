package com.cac.minibank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> processErrorResourceNotFound(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> processErrorBadRequest(BadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> processInvalidArguments(MethodArgumentNotValidException ex){

        Map<String, String> invalidArguments = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->{
            invalidArguments.put(error.getField(), error.getDefaultMessage());
        });

        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(invalidArguments);
    }


}