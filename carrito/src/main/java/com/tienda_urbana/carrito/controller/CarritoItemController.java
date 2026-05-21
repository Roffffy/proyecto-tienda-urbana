package com.tienda_urbana.carrito.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.carrito.dto.AgregarCarritoItemRequestDTO;
import com.tienda_urbana.carrito.dto.CarritoItemResponseDTO;
import com.tienda_urbana.carrito.service.CarritoItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carrito-item")
@RequiredArgsConstructor
public class CarritoItemController {

    private final CarritoItemService service;


    @PostMapping("/{id}")
    public ResponseEntity<CarritoItemResponseDTO> agregarItem(@PathVariable Long id, @Valid @RequestBody AgregarCarritoItemRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarItem(id,dto));
    }
}
