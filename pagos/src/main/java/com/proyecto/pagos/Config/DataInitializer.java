package com.proyecto.pagos.Config;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.proyecto.pagos.Model.Pagos;
import com.proyecto.pagos.Repository.PagoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInitializer implements CommandLineRunner{

    private final PagoRepository pagoRepository;

    @Override
    public void run(String... args){
        if(pagoRepository.count() > 0){
            log.info("pagos cargados. no se ejecuta el archivo");
            return;
        }

        log.info("cragando datos preconfigurados... ");
        pagoRepository.save(new Pagos(null, "BancoEstado","aprobado","debito","BE-93891", new BigDecimal("25000"), LocalDateTime.now(), 1L));

        log.info("cragando datos preconfigurados... ");
        pagoRepository.save(new Pagos(null, "BancoSantander","rechazado","debito","BE-93891", new BigDecimal("49990"), LocalDateTime.now(), 2L));
    }
}
