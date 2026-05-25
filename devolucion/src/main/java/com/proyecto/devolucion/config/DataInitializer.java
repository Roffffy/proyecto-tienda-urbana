package com.proyecto.devolucion.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyecto.devolucion.model.Devolucion;
import com.proyecto.devolucion.repository.DevolucionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner{

    private final DevolucionRepository devolucionRepository;

    @Override
    public void run(String... args){
        if(devolucionRepository.count() > 0){
            log.info("devoluciones cargadas. no se ejecuta el archivo");
            return;
        }

        log.info("cargando devoluciones preconfiguradas...");
        devolucionRepository.save(new Devolucion(null, "producto roto", "foto1.jpg" , "PENDIENTE", "foto1.jpg",LocalDateTime.now(), 11L ,2L));

        
        log.info("cargando devoluciones preconfiguradas...");
        devolucionRepository.save(new Devolucion(null, "no es lo que esperaba", "foto2.jpg" , "REPROBADO", "foto2.jpg",LocalDateTime.now(), 20L ,4L));

        
        log.info("cargando devoluciones preconfiguradas...");
        devolucionRepository.save(new Devolucion(null, "no era lo que pedi", "foto3.jpg" , "APROBADO", "foto3.jpg",LocalDateTime.now(), 21L ,1L));

        log.info("{} devolucion insertados correctamente", devolucionRepository.count());
    }

}
