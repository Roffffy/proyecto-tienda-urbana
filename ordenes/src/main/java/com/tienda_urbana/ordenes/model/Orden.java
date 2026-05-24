package com.tienda_urbana.ordenes.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDEN")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false, length = 20)
    private Long usuarioId;

    @Column(name = "total", nullable = false)
    private int total;

    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @Column(name = "creado_en", nullable = false, length = 10)
    private LocalDateTime creadoEn;

}
