package com.proyecto.notificaciones.repository;

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

import com.proyecto.notificaciones.model.Notificacion;

@DataJpaTest
@ActiveProfiles("test")
@DisplayName("test del repositorio de notificaciones en memoria")
public class NotificacionRepositoryTest {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Notificacion notificacion1;
    private Notificacion notificacion2;
    
    @BeforeEach
    void setUp(){
        notificacion1 = entityManager.persistAndFlush(new Notificacion( null,"COMPRA","email","Su compra fue realizada con éxito",true,null,1L));
        notificacion2 = entityManager.persistAndFlush(new Notificacion(null,"DEVOLUCION","sms","Su devolución fue aceptada",false,null,2L));
    }

    @Test
    @DisplayName("findAll() debe retornar todos las notificaciones insertadas")
    void findAll_debeRetornarTodosLosPNotificaciones(){
        List<Notificacion> notificaciones = notificacionRepository.findAll();

        assertNotNull(notificaciones);
        assertEquals(2, notificaciones.size());
    }

    @Test
    @DisplayName("findById() debe retornar Optional con la notificacion cuando existe")
    void findById_debeRetornarNotificaciones_cuandoExiste(){
        Optional<Notificacion> resultado = notificacionRepository.findById(notificacion1.getId());

        assertTrue(resultado.isPresent());
        assertEquals("email", resultado.get().getCanal());
    }
    @Test
    @DisplayName("findById() debe retornar Optional vacio cuando el ID no existe")
    void findById_debeRetornarVacio_cuandoNoExiste(){
        Optional<Notificacion> resultado = notificacionRepository.findById(99999L);

        assertFalse(resultado.isPresent());
    }
}
