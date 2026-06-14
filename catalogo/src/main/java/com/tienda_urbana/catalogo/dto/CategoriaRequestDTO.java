package com.tienda_urbana.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para la creacion de categorias")
public class CategoriaRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 30, message = "El nombre de la categoria no puede contener mas de 30 caracteres")
    @Schema(description = "En este esquema solo se solicita el nombre que tendra la categoria", example = "Zapatillas")
    private String nombre;
}
