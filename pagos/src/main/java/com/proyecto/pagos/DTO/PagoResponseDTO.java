package com.proyecto.pagos.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponseDTO {
    @Schema(
        description="ID único del producto, generado automáticamente",
        example="1",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private Long id;

    private String proveedor;

     @Schema(
        description="estado actual del pago, determinado automaticamente por el sistema",
        example="aprobado",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private String estado;

    private String metodoPago;

    private String referenciaExterna;

    private BigDecimal monto;

    @Schema(
        description="fecha y hora en que el pago fue creado",
        example="2026-06-17T15:30:00",
        accessMode=Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime procesadoEn;

    private Long ordenId;
}
