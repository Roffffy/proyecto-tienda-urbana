package com.proyecto.pagos.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
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

import com.proyecto.pagos.Model.Pagos;
import com.proyecto.pagos.Repository.PagoRepository;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("test del repositorio de productos en memoria")
public class PagoRepositoryTest {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Pagos laptob;
    private Pagos monitor;

    @BeforeEach
    void setUp(){
        laptob = entityManager.persistAndFlush(new Pagos(null,"BancoEstado","aprobado","debito","BE-93891",new BigDecimal("25000"),LocalDateTime.now(),1L));
        monitor = entityManager.persistAndFlush(new Pagos(null,"Mercado Pago","pendiente","credito","MP-12345",new BigDecimal("80000"),LocalDateTime.now(),2L));
    }

    @Test
    @DisplayName("findAll() debe retornar todos los productos insertados")
    void findAll_debeRetornarTodosLosProductos(){
        //logica de negocios que debe ejecutar la prueba
        List<Pagos> pagos = pagoRepository.findAll();

        //criterios de aceptación
        assertNotNull(pagos);
        assertEquals(2, pagos.size());
    }

    @Test
    @DisplayName("findById() debe retornar Optional con el producto cuando existe")
    void findById_debeRetornarPago_cuandoExiste(){
        Optional<Pagos> resultado = pagoRepository.findById(laptob.getId());

        assertTrue(resultado.isPresent());
        assertEquals("bancoestado", resultado.get().getProveedor());
    }
    @Test
    @DisplayName("findById() debe retornar Optional vacio cuando el ID no existe")
    void findById_debeRetornarVacio_cuandoNoExiste(){
        Optional<Pagos> resultado = pagoRepository.findById(99999L);

        assertFalse(resultado.isPresent());
    }
}
