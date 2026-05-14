package com.tienda_urbana.usuarios.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> validationHandler(MethodArgumentNotValidException e){
        Map<String,String> errores = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> runtimeHandler(RuntimeException e){
        Map<String,String> error = new HashMap<>();
        error.put("erro", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    
}
