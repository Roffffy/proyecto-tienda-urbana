package com.proyecto.resena.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResenaRequestDTO {

    @NotNull(message = "es obligatorio colocar su clasificaciones si quieres darle una reseña")
    @Min(value = 1, message = "la clasificacion minima es de 1 estrella")
    @Max(value = 5, message = "la clasificacion maxima es de 5 estrellas")
    @Schema(
        description="clasificacion de la reseña otorgada por el usuario (de 1 a 5 estrellas)",
        example="5",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private Integer clasificacion;

    @Size(min = 2, max = 400, message = "la cantidad de caracteres deb ser entre 2 y 400")
    @NotBlank(message = "el comentario es obligatorio")
    @Schema(
    description = "comentario de la reseña",
    example = "excelente producto, lo recomiendo",
    requiredMode = Schema.RequiredMode.REQUIRED)
    private String comentario;  

    @NotNull(message = "el usuario es obligaotrio")
    @Schema(
    description = "ID del usuario que realiza la reseña",
    example = "12",
    requiredMode = Schema.RequiredMode.REQUIRED)
    private Long usuarioId;

    @NotNull(message = "el producto es obligatorio")
    @Schema(
    description = "ID del producto reseñado",
    example = "45",
    requiredMode = Schema.RequiredMode.REQUIRED)
    private Long productoId;
}
