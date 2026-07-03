package com.proyecto.pagos.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ms.notificaciones")
public interface NotificacionesClient {
    
    @PostMapping("/api/notificaciones/{id}")
    void crearNotificacion(@PathVariable Long id);

}
