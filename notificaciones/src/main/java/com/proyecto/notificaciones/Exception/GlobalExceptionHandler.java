package com.proyecto.notificaciones.Exception;

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

    @ExceptionHandler(NotificacionNotFoundException.class)
    public  ResponseEntity<ErrorResponseDTO> manejarNotificacionEncontrada(
            NotificacionNotFoundException ex,
            HttpServletRequest request){

            logger.warn("recurso no encontrado - ID: {} | Path: {} | Mensaje: {}", 
                ex.getNotificacionId(), request.getRequestURI(), ex.getMessage());

            return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(construirError(HttpStatus.NOT_FOUND, ex.getMessage(),
            request.getRequestURI(), null));
        }

    private ErrorResponseDTO construirError(
        HttpStatus status,
        String mensaje,
        String path,
        List<String> detalles
    ){
        return new ErrorResponseDTO(
            LocalDateTime.now(),
            status.value(),
            status.getReasonPhrase(),
            mensaje,
            path,
            detalles
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponseDTO> manejarValidacion(
        MethodArgumentNotValidException ex,
        HttpServletRequest request){
             
            List<String> erroresCampos = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> String.format("campo '%s' : '%s' (valor recibido: '%s')", 
                error.getField(),
                error.getDefaultMessage(),
                error.getRejectedValue()))
            .collect(Collectors.toList());

        logger.warn("validacion fallida en {} {} - Errores: {}", 
        request.getMethod(), request.getRequestURI(), erroresCampos);

        return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(construirError(HttpStatus.BAD_REQUEST, "los datos enviados contienen error de validacion",
            request.getRequestURI(),
            erroresCampos));
        }

        @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> manejarExcepcionGeneral(
            Exception ex,
            HttpServletRequest request) {

        // logger.error con el tercer argumento "ex" imprime el stacktrace completo en el log
        logger.error("Error interno no controlado - Tipo: {} | Path: {} | Mensaje: {}",
                ex.getClass( ).getSimpleName(), request.getRequestURI(), ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(construirError(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Ocurrio un error interno del servidor. El equipo tecnico ha sido notificado.",
                        request.getRequestURI(), null));
    }
}
