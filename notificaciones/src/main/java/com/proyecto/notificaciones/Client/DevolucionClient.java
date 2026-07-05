package com.proyecto.notificaciones.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.devoluciones")
public interface DevolucionClient {

    @PostMapping("/api/devoluciones/{id}")
    void crearDevolucion(@PathVariable Long id);
}
