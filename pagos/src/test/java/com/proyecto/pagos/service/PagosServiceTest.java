package com.proyecto.pagos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyecto.pagos.DTO.PagoResponseDTO;
import com.proyecto.pagos.Model.Pagos;
import com.proyecto.pagos.Repository.PagoRepository;
import com.proyecto.pagos.Service.PagoService;

@ExtendWith(MockitoExtension.class)
@DisplayName("test unit de pagosService")
public class PagosServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    private Pagos pagoEjemplo;
    private PagoResponseDTO dtoPrueba;

    @BeforeEach
    void setUp(){
        pagoEjemplo = new Pagos(1L, "BancoEstado", "aprobado", "debito", "BE-93891",new BigDecimal("25000"), LocalDateTime.now(), 1L);
        dtoPrueba = new PagoResponseDTO(null, "BancoSantander", "rechazado", "credito", "BE-93881",new BigDecimal("2500000"), LocalDateTime.now(), 1L);
    }

    @Test
    @DisplayName("findAll() retorna la lista de pagosResponseDTO de todos los pagos")
    void findAll_debeRetornarListaDePagos(){
        when(pagoRepository.findAll()).thenReturn(List.of(pagoEjemplo));

        List<PagoResponseDTO> resultado = pagoService.obtenerPagos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("BancoEstado", resultado.get(0).getProveedor());

        verify(pagoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("findAll() debe retornar lista vacia cuando no hay pagos en MySQL")
    void findAll_debeRetornarListaVacia_SiNoHayPagos(){
        when(pagoRepository.findAll()).thenReturn(List.of());

        List<PagoResponseDTO> resultado = pagoService.obtenerPagos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }
}
