package com.proyecto.notificaciones.DTO;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionResponseDTO {
      @Schema(
        description = "identificador unico en la notificacion",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    private String tipo;

    private String canal;

    private String mensaje;

      @Schema(
        description = "indica si la notificacion fue enviada correctamente",
        example = "true",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private boolean enviado;

      @Schema(
        description = "fecha y hora en que la notificacion fue enviada",
        example = "2026-06-03T20:15:00",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime enviadoEn;

    private Long usuarioId;
}
