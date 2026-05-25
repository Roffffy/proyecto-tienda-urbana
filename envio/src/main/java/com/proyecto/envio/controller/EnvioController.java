package com.proyecto.envio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.envio.DTO.EnvioRequestDTO;
import com.proyecto.envio.DTO.EnvioResponseDTO;
import com.proyecto.envio.service.EnvioService;

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
@RequestMapping("/api/envio")
@RequiredArgsConstructor
public class EnvioController {
    private final EnvioService envioService;

    @GetMapping()
    public ResponseEntity<List<EnvioResponseDTO>> obtenerEnvio(){
      return ResponseEntity.ok(envioService.obtenerEnvios());  
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO> obtenerPorId(@PathVariable Long id){
        return envioService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<EnvioResponseDTO> crear(@Valid @RequestBody EnvioRequestDTO dto){
        return ResponseEntity.status(201).body(envioService.guardarE(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        envioService.eliminarEnvio(id);
        return ResponseEntity.noContent().build();
    }

    //obtener solo por orden
    @GetMapping("/orden/{ordenId}")
    public ResponseEntity<List<EnvioResponseDTO>>
    obtenerPorOrden(@PathVariable Long ordenId){
        return ResponseEntity.ok(
            envioService.obtenerPorOrdenId(ordenId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody EnvioRequestDTO dto){

            return envioService.actualizarEnvio(id, dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
        }
    
}
