package com.tienda_urbana.catalogo.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private ErrorResponseDTO construirError(HttpStatus status, String mensaje, String path, List<String> detalles) {
        return new ErrorResponseDTO(LocalDateTime.now(), status.value(), status.getReasonPhrase(), mensaje, path,
                detalles);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> validationHandler(MethodArgumentNotValidException e,
            HttpServletRequest request) {
        List<String> erroresCampos = e.getBindingResult().getFieldErrors().stream()
                .map(error -> String.format("Campo '$s': $s (valor recibido: '$s')", error.getField(),
                        error.getDefaultMessage(), error.getRejectedValue()))
                .collect(Collectors.toList());
        logger.warn("Validacion fallida en {} {} - Errores: {}", request.getMethod(), request.getRequestURI(), erroresCampos);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(construirError(HttpStatus.BAD_REQUEST, "Los datos enviados contiene errores de validacion", request.getRequestURI(), erroresCampos));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> generalHandler(Exception e, HttpServletRequest request){
        logger.error("Error interno no controlado - tipo: {} | Path: {} | Mensaje: {}", e.getClass().getSimpleName(), request.getRequestURI(), e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(construirError(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrio un error interno del servidor", request.getRequestURI(), null));
    }

    @ExceptionHandler(ElementoNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDTO> elementoNoEncontradoHandler(ElementoNoEncontradoException e,
            HttpServletRequest request) {
        logger.warn("{} con ID: {} | Path: {} | Mensaje: {}", e.getElemento(), e.getId(), request.getRequestURI(),
                e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(construirError(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI(), null));
    }

    @ExceptionHandler(CategoriaYaExistenteException.class)
    public ResponseEntity<ErrorResponseDTO> categoriaYaExistenteHandler(CategoriaYaExistenteException e,
            HttpServletRequest request) {
        logger.warn("Categoria '{}' existente | Path: {} | Mensaje: {}", e.getNombreCategoria(),
                request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(construirError(HttpStatus.CONFLICT, e.getMessage(), request.getRequestURI(), null));
    }

    @ExceptionHandler(SinCategoriaException.class)
    public ResponseEntity<ErrorResponseDTO> SinCategoriaHandler(SinCategoriaException e, HttpServletRequest request){
        logger.warn("Se intento eliminar la categoria con ID: 1 | Path: {} | Mensaje: {}", request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(construirError(HttpStatus.FORBIDDEN, e.getMessage(), request.getRequestURI(), null));
    }

}
