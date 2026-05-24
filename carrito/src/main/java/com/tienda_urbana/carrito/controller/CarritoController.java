package com.tienda_urbana.carrito.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.carrito.dto.AgregarItemRequestDTO;
import com.tienda_urbana.carrito.dto.CarritoResponseDTO;
import com.tienda_urbana.carrito.dto.ProductoDTO;
import com.tienda_urbana.carrito.service.CarritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService service;

    @PostMapping("/{usuarioId}")
    public ResponseEntity<ProductoDTO> agregarItem(@PathVariable Long usuarioId, @Valid @RequestBody AgregarItemRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarItem(usuarioId, dto));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<CarritoResponseDTO> verCarrito(@PathVariable Long usuarioId){
        return ResponseEntity.ok(service.verCarrito(usuarioId));
    }
}
