package com.tienda_urbana.carrito.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para mostrar la lista de productos que contiene un carrito")
public class CarritoResponseDTO {

    @Schema(description = "Lista de productos pertenecientes a un carrito",
        example = """
                {
                    "items": [
                        {
                            "productoId": 1,
                            "nombre": "Polera básica negra",
                            "categoria": "Poleras",
                            "talla": "M",
                            "precio": 14990,
                            "cantidad": 10
                        },
                        {
                            "productoId": 2,
                            "nombre": "Polera estampada rock",
                            "categoria": "Poleras",
                            "talla": "L",
                            "precio": 18990,
                            "cantidad": 10
                        },
                        {
                            "productoId": 3,
                            "nombre": "Polera oversize blanca",
                            "categoria": "Poleras",
                            "talla": "S",
                            "precio": 16990,
                            "cantidad": 10
                        }
                    ]
                }
                """
    )
    private List<ProductoDTO> items;
}
