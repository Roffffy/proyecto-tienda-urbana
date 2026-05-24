package com.tienda_urbana.carrito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgregarItemRequestDTO {

    private Long productoId;
    private int cantidad;
}
