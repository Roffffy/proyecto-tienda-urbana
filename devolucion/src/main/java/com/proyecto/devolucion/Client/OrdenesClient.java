package com.proyecto.devolucion.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.ordenes")
public interface OrdenesClient {

    @PostMapping("/api/ordenes/{id}")
    void crearOrden(@PathVariable Long id);
}
