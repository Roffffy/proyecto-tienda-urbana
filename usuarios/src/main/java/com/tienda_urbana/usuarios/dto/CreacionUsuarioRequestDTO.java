package com.tienda_urbana.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreacionUsuarioRequestDTO {

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 55, message = "El nombre no puede contener mas de 55 caracteres")
    private String nombre;

    @NotBlank(message = "El email no puede estar en blanco")
    @Size(max = 55, message = "El email no puede contener mas de 55 caracteres")
    private String email;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(max = 55, min = 8, message = "La contraseña debe contar con minimo 8 caracteres y no superar los 55")
    private String contraseña;

    @Size(max = 6, min = 6, message = "La clave debe ser de 6 caracteres")
    private String claveRecuperacion;
}
