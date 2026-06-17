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
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "identificador unico generado de forma automatica por la BD",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @NotBlank(message = "el motivo de la devolucion es obligatorio")
    @Column(nullable = false, length = 500)
    @Schema(
        description = "motivo o indicativo que da el usuario sobre su devolucion",
        example = "mi pedido llego roto"
    )
    private String motivo;

    @Column(nullable = false)
    @NotBlank(message = "la foto es obligatoria")
    @Schema(
        description = "url de la evidencia (forgrafia) para justificar su devolucion",
        example = "url2"
    )
    private String fotoEnviadaUrl;

    @Column(nullable = false)
    @Schema(
        description = "muestra el estado actual del pedidio para su devolucion",
        example = "Pendiente"
    )
    private String estado;

    @Column
    @NotBlank(message = "la etiqueta de retorno es obligatoria")
    @Schema(
        description = "etiqueta utilizada para el retorno del producto ",
        example = "url2"
    )
    private String etiquetaRetornoUrl;

    @Column(name = "solicitar_en")
    @Schema(
        description = "fecha y hora en que se solicito la fecha de devolucion",
        example = "2026-06-04T15:30:00",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime solicitadoEn;

    @Column(nullable = false)
    @Schema(
        description = "idetificador de la orden asociado al producto",
        example = "12"
    )
    private Long ordenId;

    @Column(nullable = false)
    @Schema(
        description = "identificador del usuario destinado en la devolucion",
        example = "12"
    )
    private Long usuarioId;
    
}
