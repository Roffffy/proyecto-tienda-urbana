package com.proyecto.notificaciones.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyecto.notificaciones.model.Notificacion;
import com.proyecto.notificaciones.repository.NotificacionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final NotificacionRepository notificacionRepository;

    @Override
    public void run(String... args){
        if(notificacionRepository.count() > 0){
            log.info("notificaciones cargadas. no se ejecuta el archivo");
            return;
        }
        log.info("cargando notificaciones preconfiguradas...");
        notificacionRepository.save(new Notificacion(null,"alerta","email",
        "su compra fue realizada con exito",true,LocalDateTime.now(),1L ));

        notificacionRepository.save(new Notificacion(null,"sistema","push",
        "bienvenido al sistema",false,null,2L ));

        log.info("{} notificaciones insertados correctamente", notificacionRepository.count());
    }
}
