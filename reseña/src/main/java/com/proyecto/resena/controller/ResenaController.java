package com.proyecto.resena.controller;

import org.springframework.web.bind.annotation.RestController;

import com.proyecto.resena.DTO.ResenaRequestDTO;
import com.proyecto.resena.DTO.ResenaResponseDTO;
import com.proyecto.resena.service.ResenaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    //obtener en general
    @GetMapping()
    public ResponseEntity<List<ResenaResponseDTO>> obtenerResena(){
        return ResponseEntity.ok(resenaService.obtenerResena());
    }
    
    //obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<ResenaResponseDTO> obtenerPorId(@PathVariable Long id){
        return resenaService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //crear reseña
    @PostMapping
    public ResponseEntity<ResenaResponseDTO> crear(@Valid @RequestBody ResenaRequestDTO dto

    ){
        return ResponseEntity.status(201).body(resenaService.guardarR(dto));
    }

    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        if(resenaService.obtenerPorId(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        resenaService.eliminarResena(id);
        return ResponseEntity.noContent().build();
    }

    //actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ResenaResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody ResenaRequestDTO dto){

        return resenaService.actualizarResena(id, dto)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build()); 

    }
    


}
