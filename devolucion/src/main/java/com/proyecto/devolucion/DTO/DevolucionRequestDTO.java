package com.proyecto.devolucion.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DevolucionRequestDTO {

    @NotBlank(message = "el motivo es obligatorio")
    private String motivo;

    @NotBlank(message = "la foto por url es obligatoria")
    private String fotoEnviadaUrl;

    @NotBlank(message = "la etiqueta es obligatoria")
    private String etiquetaRetornoUrl;

    @NotNull(message = "el id del orden es obligatorio")
    private Long ordenId;

    @NotNull(message = "el id del usuario es obligatorio")
    private Long usuarioId;
}
