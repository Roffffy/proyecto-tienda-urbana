package com.tienda_urbana.catalogo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.tienda_urbana.catalogo.model.Categoria;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test del repositorio de categorias en memoria")
public class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = entityManager.persistAndFlush(new Categoria(null, "Poleras"));
    }

    @Test
    @DisplayName("findById() debe retornar la categoria por ID")
    void findById_debeRetornarCategoriaPorId() {
        Optional<Categoria> resultado = repo.findById(categoria.getId());
        assertEquals(categoria.getNombre(), resultado.get().getNombre());
    }
}
