package com.proyecto.pagos.DTO;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoRequestDTO {

    @NotBlank(message = "el proveedor es obligatorio") 
    @Schema(
        description="encargado del proceso de pago",
        example="mercado pago",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private String proveedor;

    @NotBlank(message = "el metodo de pago es obligatorio")
    @Schema(
        description ="metodo utilizado para realizar el pago",
        example = "tarjeta de credito",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private String metodoPago;

    @NotNull(message = "el monto es obligatorio") 
    @Schema(
        description ="monto del pago",
        example = "$59999.00",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private BigDecimal monto;

    @NotBlank(message = "la referencia es obligatoria")
    @Schema(
        description ="referencia entregada por el porveedor del pago",
        example = "identificador 1",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private String referenciaExterna;

    @NotNull(message = "el id de la orden es obligatorio")
    @Schema(
        description ="identificador asociado al pago",
        example = "15",
        requiredMode=Schema.RequiredMode.REQUIRED
    )
    private Long ordenId;
}
