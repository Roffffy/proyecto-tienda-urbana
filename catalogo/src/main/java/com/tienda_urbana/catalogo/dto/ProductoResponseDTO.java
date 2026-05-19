package com.tienda_urbana.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO {

    private String nombre;
    private String descripcion;
    private int precio;
    private String talla;
    private int stock;
    private String categoria;
}
