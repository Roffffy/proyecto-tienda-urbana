package com.proyecto.devolucion.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.usuarios")
public interface UsuariosClient {

    @PostMapping("/api/usuarios/{id}")
    void crearUsuarios(@PathVariable Long id);
}
