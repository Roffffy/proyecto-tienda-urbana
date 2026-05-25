package com.proyecto.resena.config;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyecto.resena.model.Resena;
import com.proyecto.resena.repository.ResenaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{
    private final ResenaRepository resenaRepository;

    @Override
    public void run(String... args){
        if(resenaRepository.count() > 0){
            log.info("reseñas cargadas. no se ejecuta el archivo");
            return;
        }
        log.info("cargando reseñas preconfiguradas...");
        resenaRepository.save(new Resena(null, 5, "buen producto", LocalDateTime.of(2026,05,16,10,30), 20l, 10l));

        log.info("{} reseñas insertados correctamente", resenaRepository.count());
    }
}
