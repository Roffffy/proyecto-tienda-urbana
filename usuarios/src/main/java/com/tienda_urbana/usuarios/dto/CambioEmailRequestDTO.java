package com.tienda_urbana.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CambioEmailRequestDTO {
    
    @NotBlank(message = "El email no puede estar en blanco")
    @Size(max = 55, message = "El email no puede contener mas de 55 caracteres")
    private String nuevoEmail;
}
