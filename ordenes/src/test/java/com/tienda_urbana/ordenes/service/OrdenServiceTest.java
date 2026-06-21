package com.tienda_urbana.ordenes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tienda_urbana.ordenes.client.CarritoCliente;
import com.tienda_urbana.ordenes.dto.CarritoItemDTO;
import com.tienda_urbana.ordenes.dto.OrdenItemResponseDTO;
import com.tienda_urbana.ordenes.dto.OrdenResponseDTO;
import com.tienda_urbana.ordenes.exception.ElementoNoEncontradoException;
import com.tienda_urbana.ordenes.model.Orden;
import com.tienda_urbana.ordenes.model.OrdenItem;
import com.tienda_urbana.ordenes.repository.OrdenItemRepository;
import com.tienda_urbana.ordenes.repository.OrdenRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test del servicio de órdenes en memoria")
public class OrdenServiceTest {

    @Mock
    private OrdenItemRepository ordenItemRepo;

    @Mock
    private OrdenRepository ordenRepo;

    @Mock
    private CarritoCliente client;

    @InjectMocks
    private OrdenService service;

    private Orden orden;
    private OrdenItemResponseDTO carritoItems;

    @BeforeEach
    void setUp() {
        orden = new Orden(1L, 1L, 50000, "Procesando", LocalDateTime.now());
        carritoItems = new OrdenItemResponseDTO(Arrays.asList(
            new CarritoItemDTO(5L, "Polera", 14990, 2),
            new CarritoItemDTO(6L, "Jeans", 34990, 1)
        ));
    }

    @Test
    @DisplayName("verCarrito() debe retornar items del carrito")
    void verCarrito_debeRetornarItemsDelCarrito() {
        when(client.obtenerItemsCarrito(1L)).thenReturn(carritoItems);

        OrdenItemResponseDTO resultado = service.verCarrito(1L);

        assertEquals(2, resultado.getItems().size());
    }

    @Test
    @DisplayName("crearOrdenDeCarrito() debe crear una orden con items")
    void crearOrdenDeCarrito_debeCrearOrden() {
        when(client.obtenerItemsCarrito(1L)).thenReturn(carritoItems);
        when(ordenRepo.save(any(Orden.class))).thenAnswer(invocation -> {
            Orden o = invocation.getArgument(0);
            o.setId(1L);
            return o;
        });

        OrdenResponseDTO resultado = service.crearOrdenDeCarrito(1L, 1L);

        assertEquals(1L, resultado.getOrdenId());
        assertEquals("Procesando", resultado.getEstado());
    }

    @Test
    @DisplayName("verOrden() debe retornar orden cuando existe")
    void verOrden_debeRetornarOrdenCuandoExiste() {
        when(ordenRepo.findById(1L)).thenReturn(Optional.of(orden));
        when(client.obtenerItemsCarrito(1L)).thenReturn(carritoItems);

        OrdenResponseDTO resultado = service.verOrden(1L);

        assertEquals(1L, resultado.getOrdenId());
    }

    @Test
    @DisplayName("verOrden() lanza excepción cuando no existe")
    void verOrden_lanzaExceptionSiNoExiste() {
        when(ordenRepo.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class, () -> service.verOrden(999L));
    }
}
