package com.proyecto.devolucion.Service;

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

import com.proyecto.devolucion.DTO.DevolucionResponseDTO;
import com.proyecto.devolucion.model.Devolucion;
import com.proyecto.devolucion.repository.DevolucionRepository;
import com.proyecto.devolucion.service.DevolucionService;

@ExtendWith(MockitoExtension.class)
@DisplayName("test unit de productoService")
public class DevolucionServiceTest {

    @Mock
    private DevolucionRepository devolucionRepository;

    @InjectMocks
    private DevolucionService devolucionService;

    private Devolucion devolucionEjemplo;
    private DevolucionResponseDTO dtoPrueba;

    @BeforeEach
    void setUp(){
        devolucionEjemplo = new Devolucion(1L, "producto roto", "foto1.jpg", "pendiente", "foto1.jpg", LocalDateTime.now(), 11L, 5L);
        dtoPrueba = new DevolucionResponseDTO(null, "producto no ha llegado", "foto2.jpg", "aprobado", "foto2.jpg", LocalDateTime.now(), 12L, 6L);
    }

    @Test
    @DisplayName("findAll() retorna la lista de DTO de todos las devoluciones")
    void findAll_debeRetornarListaDeDevoluciones(){
        when(devolucionRepository.findAll()).thenReturn(List.of(devolucionEjemplo));

        List<DevolucionResponseDTO> resultado = devolucionService.obtenerDevolucion();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("producto roto", resultado.get(0).getMotivo());

        verify(devolucionRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findAll() debe retornar lista vacia cuando no hay devolucion en MySQL")
    void findAll_deberaRetornarListaVacia_SiNoHayDevoluciones(){
        when(devolucionRepository.findAll()).thenReturn(List.of());

        List<DevolucionResponseDTO> resultado = devolucionService.obtenerDevolucion();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}
