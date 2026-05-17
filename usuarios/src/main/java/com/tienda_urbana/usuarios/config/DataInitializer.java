package com.tienda_urbana.usuarios.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tienda_urbana.usuarios.model.Usuario;
import com.tienda_urbana.usuarios.repo.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository repo;

    @Override
    public void run(String... args) {


        if (repo.count()>0) {
            log.info("La Base de Datos ya cuenta con registros. Omitiendo carga de datos...");
            return;
        }

        log.info("Iniciando carga de datos...");

        repo.save(new Usuario(null, "Cristian Aguirres", "c.aguirres@gmail.com","CristianA.123",null,"Cliente",LocalDate.now()));
        repo.save(new Usuario(null, "Joseph Rosende", "j.rosende@gmail.com","rosendeJoseph_123",null,"Cliente",LocalDate.now()));

    }
    
}
