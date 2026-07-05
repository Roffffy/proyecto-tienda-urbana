package com.proyecto.resena.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.categorias")
public interface CatalogoClient {

    @PostMapping("/api/categorias/{id}")
    void crearUsuario(@PathVariable Long id);
}
