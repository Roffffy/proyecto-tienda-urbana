package com.proyecto.notificaciones.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionRequestDTO {
    
    @NotBlank(message = "el tipo es obligatorio")
    @Schema(
        description = "tipo de notificacion",
        example = "alerta",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String tipo;

    @NotBlank(message = "el canal es obligatorio")
     @Schema(
        description = "canal de envio",
        example = "email",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String canal;

    @Size(min = 2, max = 400, message = "la cantidad de caracteres no debe sobrepasar los 400 y no debe ser menos de 2")
     @Schema(
        description = "mensaje de la notificacion",
        example = "su compra fue realizada con exito",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String mensaje;

    @NotNull(message ="el usuario es obligatorio")
     @Schema(
        description = "identificador del usuario destinatario",
        example = "15",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long usuarioId;
}
