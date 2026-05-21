package com.tienda_urbana.carrito.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tienda_urbana.carrito.model.Carrito;
import com.tienda_urbana.carrito.repository.CarritoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CarritoRepository carritoRepo;



    @Override
    public void run(String... args) {


        if (carritoRepo.count()>0) {
            return;
        }

        log.info("Iniciando carga de datos de ejemplo...");

        carritoRepo.save(new Carrito(null,1L));

        
    }

}
