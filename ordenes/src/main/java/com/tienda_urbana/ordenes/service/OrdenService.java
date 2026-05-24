package com.tienda_urbana.ordenes.service;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.tienda_urbana.ordenes.client.CarritoCliente;
import com.tienda_urbana.ordenes.dto.OrdenItemResponseDTO;
import com.tienda_urbana.ordenes.dto.OrdenResponseDTO;
import com.tienda_urbana.ordenes.exception.ElementoNoEncontradoException;
import com.tienda_urbana.ordenes.model.Orden;
import com.tienda_urbana.ordenes.model.OrdenItem;
import com.tienda_urbana.ordenes.repository.OrdenItemRepository;
import com.tienda_urbana.ordenes.repository.OrdenRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdenService {

    private final OrdenItemRepository ordenItemRepo;
    private final OrdenRepository ordenRepo;
    private final CarritoCliente client;

    public OrdenItemResponseDTO verCarrito(Long id){
        return client.obtenerItemsCarrito(id);
    }

    private OrdenResponseDTO mapToDto(Orden orden){
        return new OrdenResponseDTO(orden.getId(), orden.getCreadoEn(), orden.getTotal(), orden.getEstado(), client.obtenerItemsCarrito(orden.getUsuarioId()));
    }

    public OrdenResponseDTO crearOrdenDeCarrito(Long usuarioId, Long carritoId){
        OrdenItemResponseDTO carritoItems = client.obtenerItemsCarrito(carritoId);

        int totalOrden = carritoItems.getItems().stream().mapToInt(item-> item.getPrecio()*item.getCantidad()).sum();

        Orden nuevaOrden = new Orden();
        nuevaOrden.setUsuarioId(usuarioId);
        nuevaOrden.setTotal(totalOrden);
        nuevaOrden.setEstado("Procesando");
        nuevaOrden.setCreadoEn(LocalDateTime.now());
        ordenRepo.save(nuevaOrden);

        carritoItems.getItems().forEach(item -> {
            ordenItemRepo.save(new OrdenItem(null, item.getProductoId(),item.getCantidad(),item.getPrecio(),nuevaOrden));
        });

        return new OrdenResponseDTO(nuevaOrden.getId(),nuevaOrden.getCreadoEn(),nuevaOrden.getTotal(),nuevaOrden.getEstado(),carritoItems);
    }

    public OrdenResponseDTO verOrden(Long ordenId){
        return ordenRepo.findById(ordenId).map(this::mapToDto).orElseThrow(()-> new ElementoNoEncontradoException("Orden", ordenId));
    }

}
