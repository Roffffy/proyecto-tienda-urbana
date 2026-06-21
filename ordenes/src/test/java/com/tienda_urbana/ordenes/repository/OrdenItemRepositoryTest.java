package com.tienda_urbana.ordenes.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.tienda_urbana.ordenes.model.Orden;
import com.tienda_urbana.ordenes.model.OrdenItem;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test del repositorio de items de orden en base de datos H2")
public class OrdenItemRepositoryTest {

    @Autowired
    private OrdenItemRepository repo;

    @Autowired
    private OrdenRepository ordenRepo;

    private OrdenItem item;
    private Orden orden;

    @BeforeEach
    void setUp() {
        orden = ordenRepo.save(new Orden(null, 1L, 50000, "Procesando", LocalDateTime.now()));
        item = new OrdenItem(null, 5L, 2, 14990, orden);
    }

    @Test
    @DisplayName("save() debe guardar un item de orden exitosamente")
    void save_debeGuardarItem() {
        OrdenItem guardado = repo.save(item);

        assertNotNull(guardado.getId());
        assertEquals(2, guardado.getCantidad());
        assertEquals(5L, guardado.getProductoId());
    }

    @Test
    @DisplayName("findById() debe retornar el item cuando existe")
    void findById_debeRetornarItemCuandoExiste() {
        OrdenItem guardado = repo.save(item);

        assertTrue(repo.findById(guardado.getId()).isPresent());
    }

    @Test
    @DisplayName("findAll() debe retornar todos los items")
    void findAll_debeRetornarTodosLosItems() {
        repo.save(item);
        repo.save(new OrdenItem(null, 6L, 1, 18990, orden));

        assertEquals(2, repo.findAll().size());
    }
}
