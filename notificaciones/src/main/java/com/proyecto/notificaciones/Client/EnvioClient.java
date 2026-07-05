package com.proyecto.notificaciones.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.envio")
public interface EnvioClient {
    @PostMapping("/api/envio/{id}")
    void crearEnvio(@PathVariable Long id);

}
