package com.proyecto.devolucion.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.devolucion.DTO.DevolucionRequestDTO;
import com.proyecto.devolucion.DTO.DevolucionResponseDTO;
import com.proyecto.devolucion.service.DevolucionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/devoluciones")
@RequiredArgsConstructor
public class DevolucionController {
    private final DevolucionService devolucionService;

    @GetMapping()
    public ResponseEntity<List<DevolucionResponseDTO>> obtenerDevolucion(){
        return ResponseEntity.ok(devolucionService.obtenerDevolucion());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DevolucionResponseDTO> obtenerPorId(@PathVariable Long id){
        return devolucionService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); 
    }

    @PostMapping
    public ResponseEntity<DevolucionResponseDTO> crear(@Valid @RequestBody DevolucionRequestDTO dto){
        return ResponseEntity.status(201).body(devolucionService.guardarD(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
       devolucionService.eliminarDevolucion(id);
       return ResponseEntity.noContent().build();
    } 
    
    @PutMapping("/{id}")
    public ResponseEntity<DevolucionResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody DevolucionRequestDTO dto){
            return devolucionService.actualizarDevolucion(id, dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<DevolucionResponseDTO> actualizarEstado(@PathVariable Long id, @RequestBody String estado){
        return devolucionService
        .actualizarEstado(id, estado)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
}
