package com.proyecto.devolucion.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DevolucionRequestDTO {

    @NotBlank(message = "el motivo es obligatorio")
    @Schema(
        description = "muestra el motivo en que el usuario solicita la devolucion",
        example = "el producto llego roto",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String motivo;

    @NotBlank(message = "la foto por url es obligatoria")
    @Schema(
        description = "muestra la url que es la evidencia para poder validar la devolucion",
        example = "url1",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String fotoEnviadaUrl;

    @NotBlank(message = "la etiqueta es obligatoria")
        @Schema(
        description = "muestra la etiqueta utilizada para porder retornar el producto",
        example = "url2",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String etiquetaRetornoUrl;

    @NotNull(message = "el id del orden es obligatorio")
        @Schema(
        description = "idetificador de la orden asociado al producto",
        example = "12",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long ordenId;

    @NotNull(message = "el id del usuario es obligatorio")
        @Schema(
         description = "identificador del usuario destinado en la devolucion",
        example = "12",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Long usuarioId;
}
