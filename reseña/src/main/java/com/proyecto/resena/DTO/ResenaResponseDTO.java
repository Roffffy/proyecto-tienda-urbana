package com.proyecto.resena.DTO;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResenaResponseDTO {
    @Schema(
        description="ID único del producto, generado automáticamente",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    private Integer clasificacion;

    private String comentario;

     @Schema(
        description="fecha y hora en que la reseña fue creada",
        example="2026-06-17T15:30:00",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime creadoEn;

    private Long usuarioId;

    private Long productoId;
}


