package com.proyecto.devolucion.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devolucion")
@Schema(description = "entidad que representa la reseña del sistema")
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description="Identificador único generado automáticamente por la BD",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message = "el motivo de la devolucion es obligatorio")
    @Column(nullable = false, length = 500)
    @Schema(
        description ="motivo en el cual el usuario solicita la devolucion",
        example = "el producto llego roto"
    )
    private String motivo;

    @Column(nullable = false)
    @NotBlank(message = "la foto es obligatoria")
    @Schema(
        description ="url de la imagen como evidencia del problema",
        example = "url2"
    )
    private String fotoEnviadaUrl;

    @Column(nullable = false)
    @Schema(
        description ="estado actual de la devolucion (pendiente, aprobada, rechazada)",
        example = "pendiente"
    )
    private String estado;

    @Column
    @NotBlank(message = "la etiqueta de retorno es obligatoria")
    @Schema(
        description ="url etiquetada de entorno para el envio",
        example = "url2"
    )
    private String etiquetaRetornoUrl;

    @Column(name = "solicitar_en")
    @Schema(
        description ="fecha y hora en la que se solicito la devolucion",
        example = "2026-06-17T16:30:00"
    )
    private LocalDateTime solicitadoEn;

    @Column(nullable = false)
    @Schema(
        description ="id de la orden asociada a la devolucion",
        example = "10"
    )
    private Long ordenId;

    @Column(nullable = false)
    @Schema(
        description ="id del usuario que solicita la devolucion",
        example = "5"
    )
    private Long usuarioId;
    
}
