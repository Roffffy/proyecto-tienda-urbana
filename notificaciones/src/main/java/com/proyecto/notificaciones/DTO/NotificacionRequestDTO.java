package com.proyecto.notificaciones.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NotificacionRequestDTO {
    
    @NotBlank(message = "el tipo es obligatorio")
    private String tipo;

    @NotBlank(message = "el canal es obligatorio")
    private String canal;

    @Size(min = 2, max = 400, message = "la cantidad de caracteres no debe sobrepasar los 400 y no debe ser menos de 2")
    private String mensaje;

    @NotNull(message ="el usuario es obligatorio")
    private Long usuarioId;
}
