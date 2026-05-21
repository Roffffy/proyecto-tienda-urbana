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

import com.tienda_urbana.catalogo.dto.ProductoListaResponseDTO;
import com.tienda_urbana.catalogo.dto.ProductoRequestDTO;
import com.tienda_urbana.catalogo.dto.ProductoResponseDTO;
import com.tienda_urbana.catalogo.service.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoContoller {

    private final ProductoService service;

    // Metodo para crear producto
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearProducto(dto));
    }

    // Metodo para editar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> editarProducto(@Valid @RequestBody ProductoRequestDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.editarProducto(id, dto));
    }

    // Metodo para eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        service.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado con exito");
    }

    // Metodo para ver todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoListaResponseDTO>> listarProductos(){
        return ResponseEntity.ok(service.listarProductos());
    }

    // Metodo para ver un producto por su id
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> verProducto(@PathVariable Long id){
        return ResponseEntity.ok(service.verProducto(id));
    }

    // Metodo para buscar un producto por su nombre
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<ProductoListaResponseDTO>> buscarProducto(@PathVariable String nombre){
        return ResponseEntity.ok(service.buscarProducto(nombre));
    }

    // Metodo para listar productos por una misma categoria
    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProductoListaResponseDTO>> listarPorCategoria(@PathVariable Long id){
        return ResponseEntity.ok(service.listarPorCategoria(id));
    }

}
