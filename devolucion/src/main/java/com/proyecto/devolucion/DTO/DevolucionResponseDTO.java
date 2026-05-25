package com.proyecto.devolucion.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevolucionResponseDTO {
    private Long id;

    private String motivo;

    private String fotoEnviadaUrl;

    private String estado;

    private String etiquetaRetornoUrl;

    private LocalDateTime solocitadoEn;

    private Long ordenId;

    private Long usuarioId;
}

