package com.tienda_urbana.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCarritoResponseDTO {

    private Long productoId;
    private String nombre;
    private String categoria;
    private String talla;
    private int precio;
    private int cantidad;
}
