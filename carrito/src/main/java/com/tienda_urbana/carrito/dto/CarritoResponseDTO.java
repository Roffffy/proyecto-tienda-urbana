package com.tienda_urbana.carrito.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoResponseDTO {


    private List<ProductoDTO> items;
}
