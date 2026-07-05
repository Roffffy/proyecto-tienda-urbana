package com.proyecto.devolucion.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.pagos")
public interface PagosClient {

    @PostMapping("/api/pagos/{id}")
    void crearPagos(@PathVariable Long id);
}
