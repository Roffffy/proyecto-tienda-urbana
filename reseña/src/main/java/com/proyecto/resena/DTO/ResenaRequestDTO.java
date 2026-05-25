package com.proyecto.resena.DTO;

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
    private Integer clasificacion;

    @Size(min = 2, max = 400, message = "la cantidad de caracteres deb ser entre 2 y 400")
    @NotBlank(message = "el comentario es obligatorio")
    private String comentario;  

    @NotNull(message = "el usuario es obligaotrio")
    private Long usuarioId;

    @NotNull(message = "el producto es obligatorio")
    private Long productoId;
}
