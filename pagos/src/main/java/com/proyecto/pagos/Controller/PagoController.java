package com.proyecto.pagos.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.pagos.DTO.PagoRequestDTO;
import com.proyecto.pagos.DTO.PagoResponseDTO;
import com.proyecto.pagos.Service.PagoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService pagoService;

    @GetMapping()
    public ResponseEntity<List<PagoResponseDTO>> obtenerPago(){
        return ResponseEntity.ok(pagoService.obtenerPagos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResponseDTO> obtenerPorId(@PathVariable Long id){
        return pagoService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); 
    }
    
    
    @PostMapping()
    public ResponseEntity<PagoResponseDTO> crear(@Valid @RequestBody PagoRequestDTO dto){
        return ResponseEntity.status(201).body(pagoService.guardarP(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
       pagoService.eliminarPago(id);
       return ResponseEntity.noContent().build();
    } 

     @PutMapping("/{id}")
    public ResponseEntity<PagoResponseDTO>actualizar(
        @PathVariable Long id,
        @Valid @RequestBody PagoRequestDTO dto){
            return pagoService.actualizarPago(id, dto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<PagoResponseDTO> actualizarEstado(@PathVariable Long id, @RequestBody String estado){
        return pagoService
        .actualizarEstado(id, estado)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
}
