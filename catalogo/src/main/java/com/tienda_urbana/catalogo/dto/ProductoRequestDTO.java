package com.tienda_urbana.catalogo.dto;

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
public class ProductoRequestDTO {

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 55, message = "El nombre no puede contener mas de 55 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripcion no puede contener mas de 500 caracteres")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser un numero positivo")
    private int precio;

    @NotBlank(message = "La talla no puede estar en blanco")
    @Size(max = 10, message = "La talla no puede contener mas de 10 caracteres")
    private String talla;

    @NotNull(message = "El stock no puede ser nulo")
    @Positive(message = "El stock debe ser un numero positivo")
    private int stock;

    private Long categoriaId;
}
