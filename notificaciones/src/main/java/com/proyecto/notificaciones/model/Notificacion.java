package com.proyecto.notificaciones.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "entidad que representa las notificaciones del sistema")
public class Notificacion {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Schema(
        description="Identificador único generado automáticamente por la BD",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Column(name = "tipo_clasificacion", nullable = false)
    @Schema(
        description ="tipo de notificacion segun el sistema (3j: pedido, pago, reseña, usuario)",
        example = "reseña"
    )
    private String tipo;

    @Column(name = "canal_notificacion", nullable = false)
     @Schema(
        description ="canal por el cual se envia las notificaciones (email, sms)",
        example = "email"
    )
    private String canal;

    @Column(name = "mensaje", length = 400)
     @Schema(
        description ="contenido del mensaje en la notificacion",
        example = "tu reseña fue publicada exitosamente"
    )
    private String mensaje;

    @Column(name = "enviado", nullable = false)
     @Schema(
        description ="indica si la notificacion fue enviada o no",
        example = "true"
    )
    private boolean enviado;

    @Column(name = "enviado_en")
    @Schema(
        description = "Fecha y hora en la que la notificación fue enviada",
        example = "2026-06-17T16:45:00",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime enviadoEn;

    @Column(name = "usuario_id", nullable = false)
    @Schema(
        description ="identficador del usuario que recibira las notificaciones",
        example = "12"
    )
    private Long usuarioId;
}
