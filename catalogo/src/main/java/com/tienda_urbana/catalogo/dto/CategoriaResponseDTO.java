package com.tienda_urbana.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de trasferencia de datos para visualizar el nombre de categorias")
public class CategoriaResponseDTO {

    @Schema(description = "En este campo se puede ver el nombre de la categoria", example = "Zapatillas", accessMode = Schema.AccessMode.READ_ONLY)
    private String nombre;
}
