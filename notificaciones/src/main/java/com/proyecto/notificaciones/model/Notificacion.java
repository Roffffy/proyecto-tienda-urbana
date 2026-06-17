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
@Schema(description = "entidad que representa las notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Schema(
        description = "identificador unico generado de forma automatica por la BD",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Column(name = "tipo_clasificacion", nullable = false)
    @Schema(
        description = "tipo de notificacion",
        example = "alerta"
    )
    private String tipo;

    @Column(name = "canal_notificacion", nullable = false)
     @Schema(
        description = "dara a conocer de donde se mandara la notificacion al usuario",
        example = "email"
    )
    private String canal;

    @Column(name = "mensaje", length = 400)
     @Schema(
        description = "esta funcion consiste en que quiere consultar o en que momento se dara el pedido",
        example = "su compra fue realizada con exito"
    )
    private String mensaje;

    @Column(name = "enviado", nullable = false)
     @Schema(
        description = "identificador que dira si que el estado del pedido fue enviado correctamente",
        example = "true"
    )
    private boolean enviado;

    @Column(name = "enviado_en")
     @Schema(
        description = "fecha y hora en que la notificacion fue enviada",
        example = "2026-05-20T14:30:00"
    )
    private LocalDateTime enviadoEn;

    @Column(name = "usuario_id", nullable = false)
     @Schema(
        description = "identificador del usuario en el cual es destinado en la notificacion",
        example = "15"
    )
    private Long usuarioId;
}
