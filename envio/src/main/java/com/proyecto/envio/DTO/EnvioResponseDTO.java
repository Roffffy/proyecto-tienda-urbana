package com.proyecto.envio.DTO;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioResponseDTO {
    @Schema(
        description="ID único del envio, generado automáticamente",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    private String direccion;

    private String estado;

    private String etiquetaUrl;

    private LocalDateTime despachadoEn;

    private LocalDateTime entregadoEn;

    private Long ordenId;
}
