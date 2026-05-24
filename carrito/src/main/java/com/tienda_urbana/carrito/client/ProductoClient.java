package com.tienda_urbana.carrito.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tienda_urbana.carrito.dto.ProductoDTO;

@FeignClient(name = "ms-productos", url = "${ms.productos.url}")
public interface ProductoClient {

    @GetMapping("/api/productos/{id}/enviar-carro")
    ProductoDTO obtenerProducto(@PathVariable Long id);

}
