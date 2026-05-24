package com.tienda_urbana.ordenes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.ordenes.dto.OrdenItemResponseDTO;
import com.tienda_urbana.ordenes.dto.OrdenResponseDTO;
import com.tienda_urbana.ordenes.service.OrdenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ordenes")
@RequiredArgsConstructor
public class OrdenController {

    private final OrdenService service;
    
    @GetMapping("/{id}")
    public ResponseEntity<OrdenItemResponseDTO> verCarrito(@PathVariable Long id){
        return ResponseEntity.ok(service.verCarrito(id));
    }

    @PostMapping("/carrito/{carritoId}/usuario/{usuarioId}")
    public ResponseEntity<OrdenResponseDTO> crearOrdenDeCarrito(@PathVariable Long carritoId, @PathVariable Long usuarioId){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearOrdenDeCarrito(usuarioId,carritoId));
    }

    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<OrdenResponseDTO> verOrden(@PathVariable Long ordenId){
        return ResponseEntity.ok(service.verOrden(ordenId));
    }
}
