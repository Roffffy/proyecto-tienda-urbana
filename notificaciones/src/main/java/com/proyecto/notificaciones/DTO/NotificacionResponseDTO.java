package com.proyecto.notificaciones.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionResponseDTO {
    private Long id;

    private String tipo;

    private String canal;

    private String mensaje;

    private boolean enviado;

    private LocalDateTime enviadoEn;

    private Long usuarioId;
}
