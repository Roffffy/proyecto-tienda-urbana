package com.tienda_urbana.ordenes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItemDTO {

    private Long productoId;
    private String nombre;
    private int precio;
    private int cantidad;
}
