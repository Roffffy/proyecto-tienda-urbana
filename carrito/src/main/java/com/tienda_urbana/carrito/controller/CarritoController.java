package com.tienda_urbana.carrito.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.carrito.dto.CarritoItemResponseDTO;
import com.tienda_urbana.carrito.model.Carrito;
import com.tienda_urbana.carrito.service.CarritoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carritos")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService service;

    // Metodo para crear carrito | consumido por microservicio de usuarios al crear un usuario
    @PostMapping("/{id}")
    public ResponseEntity<Carrito> crearCarrito(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearCarrito(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CarritoItemResponseDTO>> verCarritoPorUsuarioId(@PathVariable Long id){
        return ResponseEntity.ok(service.verCarritoPorUsuarioId(id));
    }
}
