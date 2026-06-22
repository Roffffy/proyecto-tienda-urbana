package com.proyecto.resena.exception;


import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //error 404
    @ExceptionHandler(ResenaNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> manejarResenaNoEncontrado(
        ResenaNotFoundException ex,
        HttpServletRequest request){

    logger.warn("Resena no encontradio por su - ID: {} | path: {} | mensaje: {}",
        ex.getResenaId(), request.getRequestId(), ex.getMessage());

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(construirError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), null));
    }

    //error 409
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> manejarJsonInvalido(
        HttpMessageNotReadableException ex,
        HttpServletRequest request){

            logger.warn("JSON invalido en la peticion - path: {} | detalle: {}",
                request.getRequestURI(), ex.getMostSpecificCause().getMessage());

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(construirError(HttpStatus.BAD_REQUEST, "El cuerpo del formato tiene un formato Json invalido o tipos de datos incorrctos", request.getRequestURI(), null));
        }

        //error 400
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDTO> manejarTipoIncorrecto(
        MethodArgumentTypeMismatchException ex,
        HttpServletRequest request){

            String mensaje = String.format(
                "El parametro '%s' con valor '%s' no puede convertirse al tipo esperado '%s'",
                ex.getMessage(), ex.getValue(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido"
            );

            logger.warn("tipo de parametro incorrecto en {} - {}", request.getRequestURI(), mensaje);

            return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(construirError(HttpStatus.BAD_REQUEST, mensaje, request.getRequestURI(), null));
            
        }

        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> manejarMetodoNoPermitido(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request) {

        logger.warn("Metodo HTTP '{}' no permitido en '{}'. Metodos permitidos: {}",
                ex.getMethod(), request.getRequestURI(),
                ex.getSupportedHttpMethods());

        String mensaje = String.format(
            "El metodo HTTP '%s' no esta permitido para este endpoint. Metodos validos: %s",
            ex.getMethod(), ex.getSupportedHttpMethods()
        );

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(construirError(HttpStatus.METHOD_NOT_ALLOWED, mensaje,
                        request.getRequestURI(), null));
    }

        @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> manejarExcepcionGeneral(
            Exception ex,
            HttpServletRequest request) {

        logger.error("Error interno no controlado - Tipo: {} | Path: {} | Mensaje: {}",
                ex.getClass().getSimpleName(), request.getRequestURI(), ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(construirError(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Ocurrio un error interno del servidor. El equipo tecnico ha sido notificado.",
                        request.getRequestURI(), null));
    }

    private ErrorResponseDTO construirError(HttpStatus status, String mensaje,
                                            String path, List<String> detalles){
        
        return new ErrorResponseDTO(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            mensaje,
            path,
            detalles
        );
    }
    
}
