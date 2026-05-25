package com.proyecto.notificaciones.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_clasificacion", nullable = false)
    private String tipo;

    @Column(name = "canal_notificacion", nullable = false)
    private String canal;

    @Column(name = "mensaje", length = 400)
    private String mensaje;

    @Column(name = "enviado", nullable = false)
    private boolean enviado;

    @Column(name = "enviado_en")
    private LocalDateTime enviadoEn;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
}
