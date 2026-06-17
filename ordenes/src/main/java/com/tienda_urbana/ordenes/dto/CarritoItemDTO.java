package com.tienda_urbana.ordenes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para obtener los datos de un producto perteneciente a un carrito")
public class CarritoItemDTO {

    @Schema(description = "En este campo se puede ver el ID del producto proveniente del carrito", example = "1")
    private Long productoId;
    @Schema(description = "Este campo representa el nombre que tiene el producto proveniente del carrito", example = "Polera oversize blanca")
    private String nombre;
    @Schema(description = "En este campo se muestra el precio correspondiente al producto", example = "16990")
    private int precio;
    @Schema(description = "En este campo se puede ver la cantidad del producto que contiene el carrito", example = "3")
    private int cantidad;
}
