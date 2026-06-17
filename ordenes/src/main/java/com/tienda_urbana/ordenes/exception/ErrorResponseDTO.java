package com.tienda_urbana.ordenes.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para informar sobre errores en la aplicaion")
public class ErrorResponseDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Este campo se autocompleta con la fecha y hora en la que ocurrio el error", example = "2026-06-14 08:27:22")
    private LocalDateTime timestamp;

    @Schema(description = "En este campo se podra visualizar el codigo http del error", examples = {"404", "500", "400"})
    private int status;

    @Schema(description = "En este campo se podra ver el nombre del error http que ocurrio", examples = {"Not Found", "Internal Server Error", "Bad Request"})
    private String error;

    @Schema(description = "En este campo se entrega el mensaje escrito por sistema para dar a entender que paso", examples = {"Ocurrio un problema interno del servidor", "Los datos enviados contienen errores de validacion"})
    private String mensaje;

    @Schema(description = "Este campo señala la ruta en la que ocurrio el error", example = "/api/usuarios")
    private String path;

    @Schema(description = "Este campo solo se utiliza para listar errores de validacion al momento de solicitar datos al usuarios", examples = {"Campo nombre: El nombre no puede estar en blanco (valor recibico: )", "Campo email: El email no puede estar en blanco (valor recibido: )"})
    private List<String> detalles;
}
