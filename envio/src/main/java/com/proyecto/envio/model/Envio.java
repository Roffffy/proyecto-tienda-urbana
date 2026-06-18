package com.proyecto.envio.model;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Envio")
@Schema(description = "entidad que representa el envio del sistema")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description="Identificador único generado automáticamente por la BD",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Column(name = "direccion", nullable = false, length = 500)
    @Schema(
        description ="direccion de la entrega del envio",
        example = "Santiago centro av huerfanos 0234"
    )
    private String direccion;

    @Column(name = "estado", nullable = false)
    @Schema(
        description ="estado actual del envio (en_camino, entregado, cancelado)",
        example = "en_camino"
    )
    private String estado;

    @Column(name = "etiqueta_url", nullable = false)
    @Schema(
        description ="url de la etiqueta en envio generada por el sistema",
        example = "url2"
    )
    private String etiquetaUrl;

    @Column(name = "depachado_en")
    @Schema(
        description ="fecha y hora en la que el pedido fue despachado",
        example = "2026-06-18T15:45:00",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime despachadoEn;

    @Column(name = "entregado_en")
    @Schema(
        description ="fecha y hora en la que el pedido fue entregado el cliente",
        example = "2026-08-18T15:15:50",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime entregadoEn;
    @Column(name = "orden_id", nullable = false)
    @Schema(
        description ="identificador de la orden asociada al envio",
        example = "25"
    )
    private Long ordenId;
}
