package com.proyecto.devolucion.model;

import java.time.LocalDateTime;

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
    private Long id;

    @NotBlank(message = "el motivo de la devolucion es obligatorio")
    @Column(nullable = false, length = 500)
    private String motivo;

    @Column(nullable = false)
    @NotBlank(message = "la foto es obligatoria")
    private String fotoEnviadaUrl;

    @Column(nullable = false)
    private String estado;

    @Column
    @NotBlank(message = "la etiqueta de retorno es obligatoria")
    private String etiquetaRetornoUrl;

    @Column(name = "solicitar_en")
    private LocalDateTime solicitadoEn;

    @Column(nullable = false)
    private Long ordenId;

    @Column(nullable = false)
    private Long usuarioId;
    
}
