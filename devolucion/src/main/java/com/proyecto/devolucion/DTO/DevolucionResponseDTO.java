package com.proyecto.devolucion.DTO;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevolucionResponseDTO {
    @Schema(
        description="ID único de la devolucion, generado automáticamente",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    private String motivo;

    private String fotoEnviadaUrl;

     @Schema(
        description="estado actual de la devolucion",
        example="pendiente",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private String estado;

    private String etiquetaRetornoUrl;

    @Schema(
        description="fecha y hora en la que se solicito la devolucion",
        example="2026-06-17T16:30:00",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime solocitadoEn;

    private Long ordenId;

    private Long usuarioId;
}

