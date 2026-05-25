package com.proyecto.pagos.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoResponseDTO {
    private Long id;

    private String proveedor;

    private String estado;

    private String metodoPago;

    private String referenciaExterna;

    private BigDecimal monto;

    private LocalDateTime procesadoEn;

    private Long ordenId;
}
