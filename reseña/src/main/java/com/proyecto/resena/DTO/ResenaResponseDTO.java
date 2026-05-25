package com.proyecto.resena.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResenaResponseDTO {
    private Long id;

    private Integer clasificacion;

    private String comentario;

    private LocalDateTime creadoEn;

    private Long usuarioId;

    private Long productoId;
}


