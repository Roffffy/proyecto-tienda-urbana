package com.tienda_urbana.usuarios.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia para los datos de respuesta del usuario")
public class VisualizarDatosUsuarioResponseDTO {

    @Schema(description = "Nombre del usuario", example = "Nicolas Saavedra", accessMode = Schema.AccessMode.READ_ONLY)
    private String nombre;
    @Schema(description = "direccion de correo electronico con la que se registra el usuario", example = "nic.saavedrag@duocuc.cl", accessMode = Schema.AccessMode.READ_ONLY)
    private String email;
    @Schema(description = "Clave para solicitar una recuperacion de cuenta", example = "123456", accessMode = Schema.AccessMode.READ_ONLY)
    private String claveRecuperacion;
    @Schema(description = "Fecha en la que el usuario creo su cuenta", example = "01-01-2026", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDate fechaCreacion;
}
