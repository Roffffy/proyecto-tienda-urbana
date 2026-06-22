package com.proyecto.notificaciones.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyecto.notificaciones.DTO.NotificacionResponseDTO;
import com.proyecto.notificaciones.model.Notificacion;
import com.proyecto.notificaciones.repository.NotificacionRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("test unit de NotificacionService")
public class NotificacionServiceTest {

    @InjectMocks
    private NotificacionService notificacionService;

    @Mock
    private NotificacionRepository notificacionRepository;

    private Notificacion notificacionEjemplo;
    private NotificacionResponseDTO dtoPrueba;

    @BeforeEach
    void setUp(){
        notificacionEjemplo = new Notificacion(1L,"COMPRA","EMAIL","su compra fue realizada con exito",true,LocalDateTime.now(),1L);
        dtoPrueba = new NotificacionResponseDTO(1L,"COMPRA", "EMAIL","su compra fue realizada con exito",true,notificacionEjemplo.getEnviadoEn(),1L);
    }

    @Test
    @DisplayName("FindAll() retorna la lista de DTO de todos los productos")
    void findAll_debeRetornarListaDeNotificaciones(){
  
        when(notificacionRepository.findAll()).thenReturn(List.of(notificacionEjemplo));

        List<NotificacionResponseDTO> resultado = notificacionService.obtenerNotificacion();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("su compra fue realizada con exito", resultado.get(0).getMensaje());

        verify(notificacionRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("findAll() debe retornar lista vacia cuando no hay productos en MySQL")
    void findAll_debeRetorarListaVacia_SiNoHayNotificaciones(){
        when(notificacionRepository.findAll()).thenReturn(List.of());

        List<NotificacionResponseDTO> resultado = notificacionService.obtenerNotificacion();
        
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

    }
}
