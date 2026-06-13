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
@Schema(description = "Objeto de transferencia para solicitar datos al momento de crear un usuario")
public class CreacionUsuarioRequestDTO {

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 55, message = "El nombre no puede contener mas de 55 caracteres")
    @Schema(description = "En este campo se solicita el nombre del usuario", example = "Nicolas Saavedra")
    private String nombre;

    @NotBlank(message = "El email no puede estar en blanco")
    @Size(max = 55, message = "El email no puede contener mas de 55 caracteres")
    @Schema(description = "En este campo se solicita la direccion de correo electronico con la que el usuario se registrar", example = "nic.saavedrag@duocuc.cl")
    private String email;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(max = 55, min = 8, message = "La contraseña debe contar con minimo 8 caracteres y no superar los 55")
    @Schema(description = "En este campo se solicita que el usuario cree una contraseña para validar su identidad", example = "ContraseñaEjemplo.123")
    private String contraseña;

    @Size(max = 6, min = 6, message = "La clave debe ser de 6 caracteres")
    @Schema(description = "En este campo se solicita crear un clave de recuperacion para solicitar una recuperacion de cuenta", example = "123456")
    private String claveRecuperacion;
}
