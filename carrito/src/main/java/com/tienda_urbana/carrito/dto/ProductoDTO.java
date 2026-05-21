package com.tienda_urbana.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private String nombre;
    private int precio;
    private String talla;
    private String categoria;

}