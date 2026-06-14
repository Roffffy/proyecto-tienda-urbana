package com.tienda_urbana.usuarios.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                .map(error -> String.format("Campo %s: %s (valor recibido: %s)", error.getField(),
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

    

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> illegalArgumentHandler(IllegalArgumentException e){
        Map<String,String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ContraseñaNoCoincideException.class)
    public ResponseEntity<ErrorResponseDTO> contraseñaNoCoincideHandler(ContraseñaNoCoincideException e, HttpServletRequest request){
        logger.warn("La contraseña ingresada no coincide con la actual | Path: {} | Mensaje: {}", request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(construirError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI(), null));
    }


    @ExceptionHandler(CorreoYaRegistradoException.class)
    public ResponseEntity<ErrorResponseDTO> correoRegistradoHandler(CorreoYaRegistradoException e, HttpServletRequest request){
        logger.warn("El correo '{}' ya se encuentra registrado | Path: {} | Mensaje: {}",e.getEmail(), request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(construirError(HttpStatus.BAD_REQUEST, e.getMessage(), request.getRequestURI(), null));
    }
    
}
