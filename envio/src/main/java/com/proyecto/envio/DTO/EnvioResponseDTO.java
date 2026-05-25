package com.proyecto.envio.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioResponseDTO {
    private Long id;

    private String direccion;

    private String estado;

    private String etiquetaUrl;

    private LocalDateTime despachadoEn;

    private LocalDateTime entregadoEn;

    private Long ordenId;
}
