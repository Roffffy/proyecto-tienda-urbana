package com.tienda_urbana.usuarios.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.usuarios.dto.CambioContraseniaRequestDTO;
import com.tienda_urbana.usuarios.dto.CambioEmailRequestDTO;
import com.tienda_urbana.usuarios.dto.CreacionUsuarioRequestDTO;
import com.tienda_urbana.usuarios.dto.VisualizarDatosUsuarioResponseDTO;
import com.tienda_urbana.usuarios.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<VisualizarDatosUsuarioResponseDTO> crearUsuario(
            @Valid @RequestBody CreacionUsuarioRequestDTO dto) {
        return ResponseEntity.status(201).body(service.crearUsuario(dto));
    }

    @GetMapping("/mi-cuenta/{id}")
    public ResponseEntity<VisualizarDatosUsuarioResponseDTO> verDatosUsuarioPorId(@PathVariable Long id) {
        return service.verDatosUsuarioPorId(id).map(datos -> ResponseEntity.ok(datos)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/mi-cuenta/modificar-contraseña/{id}")
    public ResponseEntity<String> cambiarContraseña(@Valid @RequestBody CambioContraseniaRequestDTO dto, @PathVariable Long id) {
        return service.cambiarContraseña(dto, id).map(resultado -> ResponseEntity.ok(resultado)).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/mi-cuenta/modificar-email/{id}")
    public ResponseEntity<String> cambiarEmail(@Valid @RequestBody CambioEmailRequestDTO dto, @PathVariable Long id) {
        return service.cambiarEmail(dto, id).map(resultado -> ResponseEntity.ok(resultado)).orElse(ResponseEntity.notFound().build());
    }

}
