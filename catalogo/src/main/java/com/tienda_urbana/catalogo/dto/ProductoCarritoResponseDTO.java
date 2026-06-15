package com.tienda_urbana.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Este es un objeto de transferencia de datos para enviar los datos de productos al microservicio de carrito")
public class ProductoCarritoResponseDTO {

    @Schema(description = "En este campo se muestra el ID del producto", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long productoId;
    @Schema(description = "En este campo se encuentra el nombre que tiene el producto", example = "Polera Basica Color Blanco XL", accessMode = Schema.AccessMode.READ_ONLY)
    private String nombre;
    @Schema(description = "Este campo corresponde con la categoria a la que pertenece el producto", example = "Poleras", accessMode = Schema.AccessMode.READ_ONLY)
    private String categoria;
    @Schema(description = "En este campo se puede ver la talla que tiene el producto", example = "XXL", accessMode = Schema.AccessMode.READ_ONLY)
    private String talla;
    @Schema(description = "Este campo corresponde con el precio que tiene el producto", example = "19990", accessMode = Schema.AccessMode.READ_ONLY)
    private int precio;
    @Schema(description = "Este campo es manipulado por el microservicio de carrito por lo que aqui inicialmente se le asigna 0", example = "0", accessMode = Schema.AccessMode.READ_ONLY)
    private int cantidad;
}
