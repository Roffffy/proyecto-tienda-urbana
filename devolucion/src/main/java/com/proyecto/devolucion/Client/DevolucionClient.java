package com.proyecto.devolucion.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.usuarios", url = "${ms.usuarios.url}")
public interface DevolucionClient {

    @PostMapping("/api/devoluciones/{id}")
    void crearUsuario(@PathVariable Long id);

}
