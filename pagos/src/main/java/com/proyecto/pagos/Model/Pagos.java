package com.proyecto.pagos.Model;

import java.math.BigDecimal;
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
@Table(name = "pagos")
@Schema(description = "entidad que representa la reseña del sistema")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Schema(
        description="Identificador único generado automáticamente por la BD",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Column(nullable = false)
    @Schema(
        description ="pasarela encargada del proceso de pago",
        example = "Mercado Pago"
    )
    private String proveedor;

    @Column(nullable = false)
    @Schema(
        description ="estado actual del pago",
        example = "aprobado"
    )
    private String estado;

    @Column(nullable = false)
    @Schema(
        description ="metodo utilizado para realizar el pago",
        example = "tarjeta de credito"
    )
    private String metodoPago;

    @Column(unique = false)
    @Schema(
        description ="referencia entregada por el proveedor del pago",
        example = "identificador 1"
    )
    private String referenciaExterna;

    @Column(nullable = false)
     @Schema(
        description ="monto toal del pago",
        example = "$599990.0"
    )
    private BigDecimal monto;

    @Column(name = "procesado_en")
    @Schema(
        description = "fecha y hora en que el pago fue procesado",
        example = "2026-06-17T16:45:00",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime procesadoEn;

    @Column(nullable = false)
    @Schema(
        description = "identificador de la orden asociada al pago",
        example = "15"
    )
    private Long ordenId;
}
