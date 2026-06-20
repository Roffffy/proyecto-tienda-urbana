package com.proyecto.devolucion.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DevolucionRequestDTO {

    @Schema(
        description ="motivo en el cual el usuario solicita la devolucion",
        example = "el producto llega roto",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "el motivo es obligatorio")
    private String motivo;

    @Schema(
        description ="url de la imagen como evidencia del problema",
        example = "url2",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "la foto por url es obligatoria")
    private String fotoEnviadaUrl;

    @Schema(
        description ="la etiqueta de retorno es obligatoria",
        example = "url2",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "la etiqueta es obligatoria")
    private String etiquetaRetornoUrl;

    @Schema(
        description ="id de la orden asociada a la devolucion",
        example = "10",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "el id del orden es obligatorio")
    private Long ordenId;

    @Schema(
        description ="id del usuario que solicita la devolucion",
        example = "5",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "el id del usuario es obligatorio")
    private Long usuarioId;
}

