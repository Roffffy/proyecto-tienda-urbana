package com.tienda_urbana.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoItemResponseDTO {

    private ProductoDTO producto;
    private int cantidad;
}
