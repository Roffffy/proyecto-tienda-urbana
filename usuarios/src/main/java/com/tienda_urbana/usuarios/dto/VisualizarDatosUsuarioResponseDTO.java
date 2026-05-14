package com.tienda_urbana.usuarios.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VisualizarDatosUsuarioResponseDTO {

    private String nombre;
    private String email;
    private String claveRecuperacion;
    private LocalDate fechaCreacion;
}
