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
@Schema(description = "Objeto de transferencia de datos para el cambio de email de usuario")
public class CambioEmailRequestDTO {
    
    @NotBlank(message = "El email no puede estar en blanco")
    @Size(max = 55, message = "El email no puede contener mas de 55 caracteres")
    @Schema(description = "En este campo se solicita el nuevo email que tendra el usuario", example = "nuevoemail@duocuc.cl")
    private String nuevoEmail;
}
