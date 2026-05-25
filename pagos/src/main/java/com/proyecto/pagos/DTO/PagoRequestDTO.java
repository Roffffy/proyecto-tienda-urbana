package com.proyecto.pagos.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoRequestDTO {

    @NotBlank(message = "el proveedor es obligatorio") 
    private String proveedor;

    @NotBlank(message = "el metodo de pago es obligatorio")
    private String metodoPago;

    @NotNull(message = "el monto es obligatorio") 
    private BigDecimal monto;

    @NotBlank(message = "la referencia es obligatoria")
    private String referenciaExterna;

    @NotNull(message = "el id de la orden es obligatorio")
    private Long ordenId;
}
