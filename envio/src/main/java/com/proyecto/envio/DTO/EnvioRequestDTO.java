package com.proyecto.envio.DTO;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnvioRequestDTO {

    @NotBlank(message = "el mensaje debe ser obligatorio")
    @Size(max = 500, message = "el maximo de caracteres debe de ser 500")
    @Schema(
        description ="direccion del envio donde se entrega el pedido",
        example = "Santiago centro av huerfanos 0234",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private String direccion;

    @NotBlank(message = "el estado del envio debe ser obligatorio")
    @Schema(
        description ="estado actual del envio (en_camino, entregado, cancelado)",
        example = "en_camino",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private String estado;

    @NotBlank(message = "la url de la etiqueta debe ser obligatorio")
    @Schema(
         description ="url de la etiqueta en envio generada por el sistema",
        example = "url2",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private String etiquetaUrl;

    private LocalDateTime despachadoEn;

    private LocalDateTime entregadoEn;

    @NotNull(message = "el id de la orden es obligatorio")
    @Schema(
         description ="identificador de la orden asociada al envio",
        example = "25",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private Long ordenId;
}
