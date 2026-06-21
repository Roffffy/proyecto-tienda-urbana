package com.proyecto.devolucion.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.proyecto.devolucion.model.Devolucion;
import com.proyecto.devolucion.repository.DevolucionRepository;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("test del repositorio de devoluciones en memoria")
public class DevolucionRepositoryTest {

    @Autowired
    private DevolucionRepository devolucionRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Devolucion objetoRtoto;
    private Devolucion objetoPerdido;
    

    @BeforeEach
    void setUp(){
        objetoRtoto = entityManager.persistAndFlush (new Devolucion(null, "producto roto", "foto1.jpg", "pendiente", "foto1.jpg", LocalDateTime.now(), 11L, 5L));

        objetoPerdido = entityManager.persistAndFlush (new Devolucion(null, "producto no llego", "foto1.jpg", "aprobado", "foto1.jpg", LocalDateTime.now(), 15L, 7L));
    }

    @Test
    @DisplayName("findAll() debe retornar todas las devoluciones insertados")
    void findAll_debeRetornarTodasLasDevoluciones(){
        List<Devolucion> devoluciones = devolucionRepository.findAll();

        assertNotNull(devoluciones);
        assertEquals(2, devoluciones.size());
    }

    @Test
    @DisplayName("findById() debe retornar optional con el producto cuando existe")
    void findById_debeRetornarDevolucion_cuandoExiste(){
        Optional<Devolucion> resultado = devolucionRepository.findById(objetoRtoto.getId());

        assertTrue(resultado.isPresent());
        assertEquals("producto roto", resultado.get().getMotivo());
    }
    @Test
    @DisplayName("findById() debe retornar optional con el producto cuando existe")
    void findById_debeRetornarVacio_cuandoExiste(){
        Optional<Devolucion> resultado = devolucionRepository.findById(99999L);

        assertTrue(resultado.isPresent());
    }
}
