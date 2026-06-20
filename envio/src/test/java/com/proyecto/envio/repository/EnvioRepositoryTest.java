package com.proyecto.envio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.proyecto.envio.model.Envio;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("test del repositorio de envios en memoria")
public class EnvioRepositoryTest {
    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Envio laptob;
    private Envio mouse;

    @BeforeEach
    void setUp(){
        laptob = entityManager.persistAndFlush(
            new Envio(null, "Santiago centro", "pendiente", "url2", null, null, 1L)
        );

        mouse = entityManager.persistAndFlush(
            new Envio(null, "Talca", "enviado", "url2", null, null, 5L)
        );
    }

    @Test
    @DisplayName("obtenerEnvio(), debe retornar todos los envios insertados")
    void obtenerEnvio_debeRetornarTodosLosEnvios(){
        List<Envio> envios = envioRepository.findAll();

        assertNotNull(envios);
        assertEquals(2, envios.size());
    }

    @Test
    @DisplayName("obtenerPorId() debe retornar optional con el envio cuando existe")
    void ObtenerPorId_debeRetornarEnvio_cuandoExiste(){
        Optional<Envio> resultado = envioRepository.findById(laptob.getId());

        assertTrue(resultado.isPresent());
        assertEquals("pendiente", resultado.get().getEstado());
    }
    @Test
    @DisplayName("findById() debe retornar Optional vacio cuando el ID no existe")
    void ObtenerPorId_debeRetornarVacio_cuandoNoExiste(){
        Optional<Envio> resultado = envioRepository.findById(99999L);

        assertFalse(resultado.isPresent());
    }
}
