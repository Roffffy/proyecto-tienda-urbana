package com.tienda_urbana.usuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.carritos", url = "${ms.carritos.url}")
public interface CarritoClient {

    @PostMapping("/api/carritos/{id}")
    void crearCarrito(@PathVariable Long id);

}
