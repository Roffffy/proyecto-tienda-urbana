package com.proyecto.notificaciones.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.notificaciones.DTO.NotificacionRequestDTO;
import com.proyecto.notificaciones.DTO.NotificacionResponseDTO;
import com.proyecto.notificaciones.service.NotificacionService;

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
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService notificacionService;

    @GetMapping()
    public ResponseEntity<List<NotificacionResponseDTO>> obtenerNotificacion(){
        return ResponseEntity.ok(notificacionService.obtenerNotificacion());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO> obtenerPorId(@PathVariable Long id){
        return notificacionService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crear(@Valid @RequestBody NotificacionRequestDTO dto

    ){
        return ResponseEntity.status(201).body(notificacionService.guardarN(dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(notificacionService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacionResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody NotificacionRequestDTO dto){

        return  notificacionService.actualizarNotificacion(id, dto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
}
