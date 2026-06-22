package com.proyecto.resena.model;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reseña")
@Schema(description = "entidad que representa la reseña del sistema")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description="Identificador único generado automáticamente por la BD",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Column(name = "clasificacion", nullable = false)
    @Schema(
        description ="clasificacion otorgada por el usuario hacia el producto (1 a 5 estrellas)",
        example = "5"
    )
    private Integer clasificacion;

    @Column(name = "comentario", length = 400)
    @Schema(
        description ="comentario escrito por el usuario acerca del producto",
        example = "excelente producto, llego en buenas condiciones"
    )
    private String comentario;

    @Column(name = "creado_en",nullable = false)
    @Schema(
        description = "fecha y hora en que se creo la reseña",
        example = "2026-06-17T15:30:00"
    )
    private LocalDateTime creadoEn;

    @Column(name = "usuario_id", nullable = false)
    @Schema(
        description = "identificador del usuario que realizo la reseña",
        example = "12"
    )
    private Long usuarioId;

    @Column(name = "producto_id", nullable = false)
    @Schema(
        description = "identificador del producto reseñado",
        example = "45"
    )
    private Long productoId;
}
