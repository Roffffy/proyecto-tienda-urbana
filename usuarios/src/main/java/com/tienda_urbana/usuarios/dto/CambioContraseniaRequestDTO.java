package com.tienda_urbana.usuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Objeto de transferencia de datos para cambiar de contraseña")
public class CambioContraseniaRequestDTO {

    @NotBlank(message = "Ingrese su antigua contraseña")
    @Size(max = 55, min = 8, message = "La contraseña debe contar con minimo 8 caracteres y no superar los 55")
    @Schema(description = "En este campo se solicita la contraseña antigua del usuario", example = "contraseñaAntigua.123")
    String contraseñaAntigua;

    @NotBlank(message = "Ingrese una nueva contrseña")
    @Size(max = 55, min = 8, message = "La contraseña debe contar con minimo 8 caracteres y no superar los 55")
    @Schema(description = "En este campo se solicita la contraseña nueva que tendra el usuario", example = "contraseñaNueva.321")
    String contraseñaNueva;
}
