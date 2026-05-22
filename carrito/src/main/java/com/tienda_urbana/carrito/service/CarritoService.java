package com.tienda_urbana.carrito.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tienda_urbana.carrito.dto.CarritoItemResponseDTO;
import com.tienda_urbana.carrito.exception.ElementoNoEncontradoException;
import com.tienda_urbana.carrito.model.Carrito;
import com.tienda_urbana.carrito.repository.CarritoItemRepository;
import com.tienda_urbana.carrito.repository.CarritoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository repo;
    private final CarritoItemRepository itemRepo;
    private final CarritoItemService itemService;


    public Carrito crearCarrito(Long usuarioId){
        return repo.save(new Carrito(null, usuarioId));
    }
    
    public List<CarritoItemResponseDTO> verCarritoPorUsuarioId(Long id){
        Carrito carrito = repo.findByUsuarioId(id).orElseThrow(()-> new ElementoNoEncontradoException("Carrito", id));
        return itemRepo.findByCarrito(carrito).stream().map(item -> itemService.mapToDto(item)).collect(Collectors.toList());
    }

    // agregar eliminacion al eliminar usuario

}
