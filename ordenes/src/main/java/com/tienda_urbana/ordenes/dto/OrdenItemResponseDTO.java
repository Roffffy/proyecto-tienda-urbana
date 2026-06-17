package com.tienda_urbana.ordenes.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Este objeto de transferencia de datos sirve para listar los datos de los productos contenidos en un carrito y asi poder crear la orden")
public class OrdenItemResponseDTO {

    @Schema(description = "Lista de productos provenientes de un carrito hacia una orden",
        example = """
                {
                    "items": [
                        {
                            "productoId": 1,
                            "nombre": "Polera básica negra",
                            "precio": 14990,
                            "cantidad": 1
                        },
                        {
                            "productoId": 3,
                            "nombre": "Polera oversize blanca",
                            "precio": 16990,
                            "cantidad": 1
                        },
                        {
                            "productoId": 2,
                            "nombre": "Polera estampada rock",
                            "precio": 18990,
                            "cantidad": 1
                        }
                    ]
                }
                """
    )
    private List<CarritoItemDTO> items;
}
