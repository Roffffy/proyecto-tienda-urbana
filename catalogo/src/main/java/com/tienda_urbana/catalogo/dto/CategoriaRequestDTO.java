package com.tienda_urbana.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(max = 30, message = "El nombre de la categoria no puede contener mas de 30 caracteres")
    private String nombre;
}
