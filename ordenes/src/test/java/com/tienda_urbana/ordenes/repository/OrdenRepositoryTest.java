package com.tienda_urbana.ordenes.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.tienda_urbana.ordenes.model.Orden;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@DisplayName("Test del repositorio de órdenes en base de datos H2")
public class OrdenRepositoryTest {

    @Autowired
    private OrdenRepository repo;

    private Orden orden;

    @BeforeEach
    void setUp() {
        orden = new Orden(null, 1L, 50000, "Procesando", LocalDateTime.now());
    }

    @Test
    @DisplayName("save() debe guardar una orden exitosamente")
    void save_debeGuardarOorden() {
        Orden guardada = repo.save(orden);

        assertNotNull(guardada.getId());
        assertEquals("Procesando", guardada.getEstado());
        assertEquals(1L, guardada.getUsuarioId());
    }

    @Test
    @DisplayName("findById() debe retornar una orden cuando existe")
    void findById_debeRetornarOrdenCuandoExiste() {
        Orden guardada = repo.save(orden);

        Optional<Orden> encontrada = repo.findById(guardada.getId());

        assertTrue(encontrada.isPresent());
        assertEquals(guardada.getId(), encontrada.get().getId());
    }

    @Test
    @DisplayName("findById() debe retornar empty cuando no existe")
    void findById_debeRetornarEmptyCuandoNoExiste() {
        Optional<Orden> encontrada = repo.findById(999L);

        assertFalse(encontrada.isPresent());
    }

    @Test
    @DisplayName("findAll() debe retornar todas las órdenes guardadas")
    void findAll_debeRetornarTodasLasOrdenes() {
        repo.save(orden);
        repo.save(new Orden(null, 2L, 60000, "Completada", LocalDateTime.now()));

        assertEquals(2, repo.findAll().size());
    }
}
