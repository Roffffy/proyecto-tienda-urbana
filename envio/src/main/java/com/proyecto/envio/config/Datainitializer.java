package com.proyecto.envio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyecto.envio.model.Envio;
import com.proyecto.envio.repository.EnvioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Datainitializer implements CommandLineRunner{
    private final EnvioRepository envioRepository;

    @Override
    public void run(String... args){
        if(envioRepository.count()>0){
            log.info("envios cargados, no se ejecuta el archivo");
            return;
        }
        log.info("cargando envios preconfigurados");
        envioRepository.save(new Envio(null, "Santiago Centro", "pendiente", "url2", null, null, 1L));

        log.info("{} envios insertados correctamente", envioRepository.count());
    }

}
