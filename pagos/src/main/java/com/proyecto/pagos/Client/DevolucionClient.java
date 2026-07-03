package com.proyecto.pagos.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.devolucion")
public interface DevolucionClient {

    @PostMapping("/api/devolucion/{id}")
    void crearDevolucion(@PathVariable Long id);
}
