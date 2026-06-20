package com.proyecto.envio.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.proyecto.envio.DTO.EnvioResponseDTO;
import com.proyecto.envio.model.Envio;
import com.proyecto.envio.repository.EnvioRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("test unit de envioService")
public class EnvioServiceTest {
    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    private Envio envioEjemplo;
    private EnvioResponseDTO dtoPrueba;

    @BeforeEach
    void setUp(){
        envioEjemplo = new Envio(1L, "santiago centro", "pendiente", "url2", null,null,1L);
        dtoPrueba = new EnvioResponseDTO(2L, "santiago centro", "pendiente", "url2", null,null,2L);
    }

    @Test
    @DisplayName("obtenerEnvio() retorna la lista de dto de todos los productos")
    void findAll_debeRetornarListaDeEnvios(){
        when(envioRepository.existsById(1L)).thenReturn(true);
        when(envioRepository.findById(1L)).thenReturn(Optional.of(envioEjemplo));

        Optional<EnvioResponseDTO> resultado = envioService.obtenerPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("santiago centro", resultado.get().getDireccion());

        verify(envioRepository).existsById(1L);
        verify(envioRepository).findById(1L);

    }

    @Test
    @DisplayName("findAll() debe retornar lista vacia cuando no hay envios en MySQL")
    void findAll_debeRetorarListaVacia_SiNoHayProductos(){
        //GIVEN
        when(envioRepository.findAll()).thenReturn(List.of());

        //WHEN
        List<EnvioResponseDTO> resultado = envioService.obtenerEnvios();
        //Criterios de aceptación
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

    }

}
