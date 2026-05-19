package com.tienda_urbana.catalogo.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ErrorResponseDTO construirError(HttpStatus status, String mensaje, String path, List<String> detalles){
        return new ErrorResponseDTO(LocalDateTime.now(), status.value(), status.getReasonPhrase(), mensaje, path, detalles);
    }

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
        error.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(ElementoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> elementoNoEncontradoHandler(ElementoNoEncontradoException e, HttpServletRequest request){
        logger.warn("{} con ID: {} | Path: {} | Mensaje: {}", e.getElemento(), request.getRequestURI(), e.getId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(construirError(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI(), null));
    }

    @ExceptionHandler(CategoriaYaExistenteException.class)
    public ResponseEntity<ErrorResponseDTO> categoriaYaExistenteHandler(CategoriaYaExistenteException e, HttpServletRequest request){
        logger.warn("Categoria '{}' existente | Path: {} | Mensaje: {}", e.getNombreCategoria(), request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(construirError(HttpStatus.CONFLICT, e.getMessage(), request.getRequestURI(), null));
    }

}
