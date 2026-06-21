package com.tienda_urbana.catalogo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.tienda_urbana.catalogo.model.Categoria;
import com.tienda_urbana.catalogo.model.Producto;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("Test del repositorio de productos en memoria")
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    private Categoria categoria;
    private Producto producto;

    @BeforeEach
    void setUp() {
        categoria = entityManager.persistAndFlush(new Categoria(null, "Poleras"));
        producto = entityManager.persistAndFlush(new Producto(null, "Polera", "Descripcion", 14990, "M", 10, categoria));
    }

    @Test
    @DisplayName("findByCategoria() debe retornar productos por categoria")
    void findByCategoria_debeRetornarProductosPorCategoria() {
        assertEquals(1, repo.findByCategoria(categoria).size());
    }

    @Test
    @DisplayName("buscarProducto() debe retornar productos cuyo nombre contiene el texto")
    void buscarProducto_debeRetornarProductosPorNombre() {
        assertEquals(1, repo.buscarProducto("Pol").size());
    }
}
