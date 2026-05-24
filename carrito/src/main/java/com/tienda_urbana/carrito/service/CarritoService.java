package com.tienda_urbana.carrito.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tienda_urbana.carrito.client.ProductoClient;
import com.tienda_urbana.carrito.dto.AgregarItemRequestDTO;
import com.tienda_urbana.carrito.dto.CarritoResponseDTO;
import com.tienda_urbana.carrito.dto.ProductoDTO;
import com.tienda_urbana.carrito.exception.ElementoNoEncontradoException;
import com.tienda_urbana.carrito.model.Carrito;
import com.tienda_urbana.carrito.model.CarritoItem;
import com.tienda_urbana.carrito.repository.CarritoItemRepository;
import com.tienda_urbana.carrito.repository.CarritoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CarritoService {

    private final CarritoItemRepository itemRepo;
    private final CarritoRepository carritoRepo;
    private final ProductoClient client;

    private ProductoDTO mapToDto(CarritoItem carritoItem){
        ProductoDTO dto = client.obtenerProducto(carritoItem.getProductoId());
        return new ProductoDTO(carritoItem.getProductoId(), dto.getNombre(), dto.getCategoria(), dto.getTalla(), dto.getPrecio(),carritoItem.getCantidad());
    }

    public ProductoDTO agregarItem(Long usuarioId, AgregarItemRequestDTO dto){
        Carrito carrito = carritoRepo.findByUsuarioId(usuarioId).orElseThrow(()-> new ElementoNoEncontradoException("Usuario", usuarioId));
        return mapToDto(itemRepo.save(new CarritoItem(null, dto.getCantidad(), dto.getProductoId(), carrito)));
    }
    
    // falta agregar metodo para eliminar item del carro y validaciones como si el item ya esta aumentar stock

    public CarritoResponseDTO verCarrito(Long usuarioId){
        Carrito carrito = carritoRepo.findByUsuarioId(usuarioId).orElseThrow(()-> new ElementoNoEncontradoException("Usuario", usuarioId));
        CarritoResponseDTO respuesta = new CarritoResponseDTO();
        
        respuesta.setItems(itemRepo.findByCarrito(carrito).stream().map(this::mapToDto).collect(Collectors.toList()));

        return respuesta;
    }


}
