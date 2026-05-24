package com.tienda_urbana.ordenes.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tienda_urbana.ordenes.dto.OrdenItemResponseDTO;

@FeignClient(name = "ms-carritos",url = "${ms.carritos.url}")
public interface CarritoCliente {

    @GetMapping("/api/carrito/{carritoId}")
    OrdenItemResponseDTO obtenerItemsCarrito(@PathVariable Long carritoId);



}
