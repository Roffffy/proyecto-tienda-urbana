package com.tienda_urbana.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Este objeto de transferencia de datos sirve para listar varios productos y mostrarlos")
public class ProductoListaResponseDTO {

    @Schema(description = "En este campo se muestra el nombre de cada producto", example = "Pantalon cargo color negro", accessMode = Schema.AccessMode.READ_ONLY)
    private String nombre;
    @Schema(description = "En este campo se muestra el precio de cada producto", example = "14990", accessMode = Schema.AccessMode.READ_ONLY)
    private int precio;
    @Schema(description = "En este campo se muestra la categoria a la que pertenece cada producto", example = "Pantalones", accessMode = Schema.AccessMode.READ_ONLY)
    private String categoria;
}
