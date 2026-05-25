package com.proyecto.envio.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnvioRequestDTO {

    @NotBlank(message = "el mensaje debe ser obligatorio")
    @Size(max = 500, message = "el maximo de caracteres debe de ser 500")
    private String direccion;

    @NotBlank(message = "el estado del envio debe ser obligatorio")
    private String estado;

    @NotBlank(message = "la url de la etiqueta debe ser obligatorio")
    private String etiquetaUrl;

    private LocalDateTime despachadoEn;

    private LocalDateTime entregadoEn;

    @NotNull(message = "el id de la orden es obligatorio")
    private Long ordenId;
}
