package com.tienda_urbana.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Este objeto de transferencia de datos sirve para solicitar los datos que tendra un nuevo producto al momento de crearlo")
public class ProductoRequestDTO {

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 55, message = "El nombre no puede contener mas de 55 caracteres")
    @Schema(description = "En este campo se solicita el nombre que tendra el producto", example = "Polera OverSize Color negro")
    private String nombre;

    @Size(max = 500, message = "La descripcion no puede contener mas de 500 caracteres")
    @Schema(description = "En este campo se solicita una descripcion del producto en caso de contar con una", example = "Polera 100% de algodon talla XLL hombres color verde")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser un numero positivo")
    @Schema(description = "En este campo se solicita el precio que tendra el prdoucto", example = "19990")
    private int precio;

    @NotBlank(message = "La talla no puede estar en blanco")
    @Size(max = 10, message = "La talla no puede contener mas de 10 caracteres")
    @Schema(description = "En este campo se solicita la talla del producto", example = "L")
    private String talla;

    @NotNull(message = "El stock no puede ser nulo")
    @Positive(message = "El stock debe ser un numero positivo")
    @Schema(description = "En este campo se solicita la cantidad inicial de unidades existentes del producto", example = "45")
    private int stock;

    @Schema(description = "En este campo se solicita el id de la categoria a la que va a pertenecer el producto", example = "1")
    private Long categoriaId;
}
