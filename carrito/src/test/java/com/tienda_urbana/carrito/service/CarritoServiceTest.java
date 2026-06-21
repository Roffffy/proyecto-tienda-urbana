package com.tienda_urbana.carrito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tienda_urbana.carrito.client.ProductoClient;
import com.tienda_urbana.carrito.dto.AgregarItemRequestDTO;
import com.tienda_urbana.carrito.dto.ProductoDTO;
import com.tienda_urbana.carrito.exception.ElementoNoEncontradoException;
import com.tienda_urbana.carrito.model.Carrito;
import com.tienda_urbana.carrito.model.CarritoItem;
import com.tienda_urbana.carrito.repository.CarritoItemRepository;
import com.tienda_urbana.carrito.repository.CarritoRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test del servicio de carrito en memoria")
public class CarritoServiceTest {

    @Mock
    private CarritoItemRepository itemRepo;

    @Mock
    private CarritoRepository carritoRepo;

    @Mock
    private ProductoClient client;

    @InjectMocks
    private CarritoService service;

    private Carrito carrito;
    private CarritoItem item;

    @BeforeEach
    void setUp() {
        carrito = new Carrito(1L, 1L);
        item = new CarritoItem(1L, 2, 1L, carrito);
    }

    @Test
    @DisplayName("agregarItem() debe retornar producto cuando el carrito existe")
    void agregarItem_debeRetornarProductoCuandoElCarritoExiste() {
        when(carritoRepo.findByUsuarioId(1L)).thenReturn(Optional.of(carrito));
        when(itemRepo.save(any(CarritoItem.class))).thenAnswer(invocation -> {
            CarritoItem arg = invocation.getArgument(0);
            arg.setId(1L);
            return arg;
        });
        when(client.obtenerProducto(1L)).thenReturn(new ProductoDTO(1L, "Polera", "Poleras", "M", 14990, 0));

        ProductoDTO resultado = service.agregarItem(1L, new AgregarItemRequestDTO(1L, 2));

        assertEquals(1L, resultado.getProductoId());
        assertEquals(2, resultado.getCantidad());
        assertEquals("Poleras", resultado.getCategoria());
    }

    @Test
    @DisplayName("agregarItem() lanza ElementoNoEncontradoException cuando no existe carrito")
    void agregarItem_lanzaExceptionCuandoNoExisteCarrito() {
        when(carritoRepo.findByUsuarioId(99L)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class,
                () -> service.agregarItem(99L, new AgregarItemRequestDTO(1L, 2)));
    }

    @Test
    @DisplayName("verCarrito() debe retornar carrito con items cuando existe")
    void verCarrito_debeRetornarCarritoConItems() {
        when(carritoRepo.findByUsuarioId(1L)).thenReturn(Optional.of(carrito));
        when(itemRepo.findByCarrito(carrito)).thenReturn(List.of(item));
        when(client.obtenerProducto(1L)).thenReturn(new ProductoDTO(1L, "Polera", "Poleras", "M", 14990, 0));

        assertEquals(1, service.verCarrito(1L).getItems().size());
    }

    @Test
    @DisplayName("verCarrito() lanza ElementoNoEncontradoException cuando no existe carrito")
    void verCarrito_lanzaExceptionCuandoNoExisteCarrito() {
        when(carritoRepo.findByUsuarioId(100L)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class, () -> service.verCarrito(100L));
    }
}
