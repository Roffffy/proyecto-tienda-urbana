package com.tienda_urbana.carrito.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Objeto de transferencia de datos para agregar productos al carrito")
public class AgregarItemRequestDTO {

    @NotNull(message = "Se debe ingresar un ID de producto a agregar")
    @Min(1)
    @Schema(description = "En este campo se solicita el ID del producto que se desea agregar", example = "10")
    private Long productoId;

    @NotNull(message = "Se debe ingrear la cantidad del producto a agregar")
    @Min(1)
    @Schema(description = "En este campo se debe ingresar la cantidad del producto que se esta agregando", example = "2")
    private int cantidad;
}
