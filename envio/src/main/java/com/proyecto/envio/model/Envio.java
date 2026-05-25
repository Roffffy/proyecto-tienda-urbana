package com.proyecto.envio.model;

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
@Table(name = "Envio")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "direccion", nullable = false, length = 500)
    private String direccion;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "etiqueta_url", nullable = false)
    private String etiquetaUrl;

    @Column(name = "depachado_en")
    private LocalDateTime despachadoEn;

    @Column(name = "entregado_en")
    private LocalDateTime entregadoEn;

    @Column(name = "orden_id", nullable = false)
    private Long ordenId;
}
