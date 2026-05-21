package com.tienda_urbana.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// agregar validaciones
public class AgregarCarritoItemRequestDTO {

    private int cantidad;
    private Long productoId;
}
