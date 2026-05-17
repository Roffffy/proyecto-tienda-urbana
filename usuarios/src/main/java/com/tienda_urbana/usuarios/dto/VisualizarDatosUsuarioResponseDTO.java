package com.tienda_urbana.usuarios.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisualizarDatosUsuarioResponseDTO {

    private String nombre;
    private String email;
    private String claveRecuperacion;
    private LocalDate fechaCreacion;
}
