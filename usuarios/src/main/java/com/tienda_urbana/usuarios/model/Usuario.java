package com.tienda_urbana.usuarios.model;

import java.time.LocalDate;

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
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 55)
    private String nombre;

    @Column(name = "email", nullable = false, length = 55, unique = true)
    private String email;

    @Column(name = "contrasena", nullable = false, length = 55)
    private String contrasena;

    @Column(name = "clave_recuperacion", nullable = true, length = 6)
    private String claveRecuperacion;

    @Column(name = "rol", nullable = false, length = 30)
    private String rol;

    @Column(name = "fecha_creacion", nullable = false, length = 10)
    private LocalDate fechaCreacion;
}
