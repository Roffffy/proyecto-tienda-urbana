package com.tienda_urbana.catalogo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tienda_urbana.catalogo.dto.CategoriaResponseDTO;
import com.tienda_urbana.catalogo.dto.CategoriaRequestDTO;
import com.tienda_urbana.catalogo.service.CategoriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;
    
    // Metodo para crear categoria
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(@Valid @RequestBody CategoriaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearCategoria(dto));
    }

    // Metodo para editar categoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> editarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDTO dto){
        return ResponseEntity.ok(service.editarCategoria(id, dto));
    }

    // Metodo para ver todas las categorias
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> verCategorias(){
        return ResponseEntity.ok(service.verCategorias());
    }

    // Metodo para buscar categoria por su nombre
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<CategoriaResponseDTO>> buscarCategoria(@PathVariable String nombre){
        return ResponseEntity.ok(service.buscarCategoria(nombre));
    }

    // Metodo para eliminar categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id){
        service.eliminarCategoria(id);
        return ResponseEntity.ok("Categoria eliminada con exito");
    }
}
