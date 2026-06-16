package com.tienda_urbana.carrito.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para manipular los datos recibidos por el microservicio de catalogo")
public class ProductoDTO {

    @Schema(description = "Muestra el ID del producto recibido", example = "2")
    private Long productoId;
    @Schema(description = "Muestra el nombre del producto recibido", example = "Polera estampada rock")
    private String nombre;
    @Schema(description = "Muestra el nombre de la categoria a la que pertenece el producto recibido", example = "Poleras")
    private String categoria;
    @Schema(description = "Muestra la talla del producto recibido", example = "XXL")
    private String talla;
    @Schema(description = "Muestra el precio que tiene el producto recibido", example = "18990")
    private int precio;
    @Schema(description = "Este campo si lo manipula el microservicio de carrito al momento de agregar un producto para determinar la cantidad solicitada", example = "3")
    private int cantidad;

}