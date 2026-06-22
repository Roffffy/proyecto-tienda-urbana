package com.proyecto.resena.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

import com.proyecto.resena.model.Resena;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("dest del repositorio de reseñas en memoria")
public class ReseñaRepositoryTest {
    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Resena resena1;
    private Resena resena2;

    @BeforeEach
    void setUp(){
        resena1 = entityManager.persistAndFlush(new Resena(null,5,"muy buen producto",LocalDateTime.of(2026, 6, 17, 15, 30),1L,10L));
        resena2 = entityManager.persistAndFlush(new Resena( null,3,"producto aceptable",LocalDateTime.of(2026, 6, 18, 10, 15),2L,11L));
    }

    @Test
    @DisplayName("findAll() debe retornar todas las reseñas insertadas")
    void findAll_debeRetornarTodosLasReseñas(){
        List<Resena> reseñas = resenaRepository.findAll();

        assertNotNull(reseñas);
        assertEquals(2, reseñas.size());
    }

    @Test
    @DisplayName("findById() debe retornar Optional con la reseña cuando existe")
    void findById_debeRetornarReseña_cuandoExiste(){
        Optional<Resena> resultado = resenaRepository.findById(resena1.getId());

        assertTrue(resultado.isPresent());
        assertEquals("muy buen producto", resultado.get().getComentario());
    }
    @Test
    @DisplayName("findById() debe retornar Optional vacio cuando el ID no existe")
    void findById_debeRetornarVacio_cuandoNoExiste(){
        Optional<Resena> resultado = resenaRepository.findById(99999L);

        assertFalse(resultado.isPresent());
    }

}
