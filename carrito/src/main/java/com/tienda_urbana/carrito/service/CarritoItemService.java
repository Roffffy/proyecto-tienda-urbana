package com.tienda_urbana.carrito.service;

import org.springframework.stereotype.Service;

import com.tienda_urbana.carrito.client.ProductoClient;
import com.tienda_urbana.carrito.dto.AgregarCarritoItemRequestDTO;
import com.tienda_urbana.carrito.dto.CarritoItemResponseDTO;
import com.tienda_urbana.carrito.dto.ProductoDTO;
import com.tienda_urbana.carrito.exception.ElementoNoEncontradoException;
import com.tienda_urbana.carrito.model.Carrito;
import com.tienda_urbana.carrito.model.CarritoItem;
import com.tienda_urbana.carrito.repository.CarritoItemRepository;
import com.tienda_urbana.carrito.repository.CarritoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoItemService {

    private final CarritoItemRepository itemRepo;
    private final CarritoRepository carroRepo;
    private final ProductoClient cliente;

    private ProductoDTO obtenerProducto(Long id){
        return cliente.obtenerPorId(id);
    }


    public CarritoItemResponseDTO mapToDto(CarritoItem carritoItem){
        return new CarritoItemResponseDTO(obtenerProducto(carritoItem.getProductoId()),carritoItem.getCantidad());
    }

    public CarritoItemResponseDTO agregarItem(Long carritoId, AgregarCarritoItemRequestDTO dto){
        Carrito carrito = carroRepo.findById(carritoId).orElseThrow(()-> new ElementoNoEncontradoException("Carrito", carritoId));
        return mapToDto(itemRepo.save(new CarritoItem(null, dto.getCantidad(), dto.getProductoId(),carrito)));
    }

    public void eliminarItem(Long id){

    }
    
}
