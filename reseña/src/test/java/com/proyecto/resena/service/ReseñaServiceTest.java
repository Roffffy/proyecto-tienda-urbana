package com.proyecto.resena.service;

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

import com.proyecto.resena.DTO.ResenaResponseDTO;
import com.proyecto.resena.model.Resena;
import com.proyecto.resena.repository.ResenaRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("test unit de ReseñaService")
public class ReseñaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @InjectMocks
    private ResenaService resenaService;

    private Resena resenaEjemplo;

    @BeforeEach
    void setUp(){
        resenaEjemplo = new Resena(1L,5,"buen producto",LocalDateTime.of(2026, 6, 17, 15, 30),12L,45L);
    }
    @Test
    @DisplayName("FindAll() retorna la lista de DTO de todos las reseñas")
    void findAll_debeRetornarListaDeReseñas(){

        when(resenaRepository.findAll()).thenReturn(List.of(resenaEjemplo));


        List<ResenaResponseDTO> resultado = resenaService.obtenerResena();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("buen producto", resultado.get(0).getComentario());

        verify(resenaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findAll() debe retornar lista vacia cuando no hay reseñas en MySQL")
    void findAll_debeRetorarListaVacia_SiNoHayReseñas(){
       
        when(resenaRepository.findAll()).thenReturn(List.of());

        List<ResenaResponseDTO> resultado = resenaService.obtenerResena();
  
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

    }
}
