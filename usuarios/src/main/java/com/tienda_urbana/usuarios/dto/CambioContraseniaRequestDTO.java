package com.tienda_urbana.usuarios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CambioContraseniaRequestDTO {

    @NotBlank(message = "ingrese su antigua contraseña")
    @Size(max = 55, min = 8, message = "La contraseña debe contar con minimo 8 caracteres y no superar los 55")
    String contraseñaAntigua;

    @NotBlank(message = "Ingrese una nueva contrseña")
    @Size(max = 55, min = 8, message = "La contraseña debe contar con minimo 8 caracteres y no superar los 55")
    String contraseñaNueva;
}
