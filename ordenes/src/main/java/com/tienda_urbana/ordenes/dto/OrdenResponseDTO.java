package com.tienda_urbana.ordenes.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para mostrar una orden")
public class OrdenResponseDTO {

    @Schema(description = "En este campo se muestra el ID de la orden", example = "1")
    private Long ordenId;
    @Schema(description = "En este campo se muestra la fecha en la que fue creada la orden", example = "2026-06-16T21:35:42.219649")
    private LocalDateTime fecha;
    @Schema(description = "En este campo se muestra el valor total de la orden", example = "50970")
    private int total;
    @Schema(description = "En este campo se muestra el estado en el que se encuentra la orden", example = "Procesando")
    private String estado;
    @Schema(type = "array", description = "En este campo se muestra una lista de los items que contiene la orden",
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
    private OrdenItemResponseDTO items;
}
