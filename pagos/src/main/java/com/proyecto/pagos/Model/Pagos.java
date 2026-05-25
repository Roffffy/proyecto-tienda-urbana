package com.proyecto.pagos.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String proveedor;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String metodoPago;

    @Column(unique = false)
    private String referenciaExterna;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(name = "procesado_en")
    private LocalDateTime procesadoEn;

    @Column(nullable = false)
    private Long ordenId;
}
