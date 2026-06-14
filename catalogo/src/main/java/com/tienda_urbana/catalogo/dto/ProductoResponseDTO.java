package com.tienda_urbana.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Este objeto de transferencia de datos sirve para visualizar productos y sus datos")
public class ProductoResponseDTO {

    @Schema(description = "En este campo se visualiza el numero de ID que tiene el producto", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "En este campo se puede ver el nombre del producto", example = "Chaqueta de cuero color negro")
    private String nombre;
    @Schema(description = "En este campo se muestra la descripcion del producto en caso de contar con una", example = "Chaqueta de cuero 100% sintetico talla XL", accessMode = Schema.AccessMode.READ_ONLY)
    private String descripcion;
    @Schema(description = "En este campo se puede ver el precio que tiene el producto", example = "29990", accessMode = Schema.AccessMode.READ_ONLY)
    private int precio;

    @Schema(description = "En este campo se muestra la talla que tiene el producto", example = "XXL", accessMode = Schema.AccessMode.READ_ONLY)
    private String talla;
    @Schema(description = "En este campo se muestra el stock actual que tiene el producto", example = "12", accessMode = Schema.AccessMode.READ_ONLY)
    private int stock;
    @Schema(description = "En este campo se muestra el nombre de la categoria a la que pertecene el producto", example = "Chaquetas", accessMode = Schema.AccessMode.READ_ONLY)
    private String categoria;
}
