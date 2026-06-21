package com.tienda_urbana.catalogo.service;

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

import com.tienda_urbana.catalogo.dto.ProductoRequestDTO;
import com.tienda_urbana.catalogo.dto.ProductoResponseDTO;
import com.tienda_urbana.catalogo.dto.ProductoListaResponseDTO;
import com.tienda_urbana.catalogo.exception.ElementoNoEncontradoException;
import com.tienda_urbana.catalogo.model.Categoria;
import com.tienda_urbana.catalogo.model.Producto;
import com.tienda_urbana.catalogo.repository.CategoriaRepository;
import com.tienda_urbana.catalogo.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test del servicio de productos en memoria")
public class ProductoServiceTest {

    @Mock
    private ProductoRepository prodRepo;

    @Mock
    private CategoriaRepository catRepo;

    @InjectMocks
    private ProductoService service;

    private Categoria categoria;
    private Producto producto;

    @BeforeEach
    void setUp() {
        categoria = new Categoria(1L, "Poleras");
        producto = new Producto(1L, "Polera", "Descripcion", 14990, "M", 10, categoria);
    }

    @Test
    @DisplayName("crearProducto() debe retornar dto cuando la categoria existe")
    void crearProducto_debeRetornarDtoCuandoCategoriaExiste() {
        when(catRepo.findById(1L)).thenReturn(Optional.of(categoria));
        when(prodRepo.save(any(Producto.class))).thenAnswer(invocation -> {
            Producto p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });

        ProductoResponseDTO resultado = service.crearProducto(new ProductoRequestDTO("Polera", "Descripcion", 14990, "M", 10, 1L));

        assertEquals("Polera", resultado.getNombre());
        assertEquals("Poleras", resultado.getCategoria());
    }

    @Test
    @DisplayName("crearProducto() lanza ElementoNoEncontradoException cuando la categoria no existe")
    void crearProducto_lanzaExceptionSiCategoriaNoExiste() {
        when(catRepo.findById(5L)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class,
                () -> service.crearProducto(new ProductoRequestDTO("Polera", "Descripcion", 14990, "M", 10, 5L)));
    }

    @Test
    @DisplayName("editarProducto() debe retornar producto actualizado")
    void editarProducto_debeRetornarProductoActualizado() {
        Producto productoExistente = new Producto(2L, "Polera", "Descripcion", 14990, "M", 10, categoria);
        when(catRepo.findById(1L)).thenReturn(Optional.of(categoria));
        when(prodRepo.findById(2L)).thenReturn(Optional.of(productoExistente));
        when(prodRepo.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProductoResponseDTO resultado = service.editarProducto(2L, new ProductoRequestDTO("Polera nueva", "Desc nueva", 15990, "L", 20, 1L));

        assertEquals("Polera nueva", resultado.getNombre());
        assertEquals(15990, resultado.getPrecio());
    }

    @Test
    @DisplayName("editarProducto() lanza ElementoNoEncontradoException cuando el producto no existe")
    void editarProducto_lanzaExceptionSiProductoNoExiste() {
        when(catRepo.findById(1L)).thenReturn(Optional.of(categoria));
        when(prodRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class,
                () -> service.editarProducto(99L, new ProductoRequestDTO("Polera", "Descripcion", 14990, "M", 10, 1L)));
    }

    @Test
    @DisplayName("editarProducto() lanza ElementoNoEncontradoException cuando la categoria no existe")
    void editarProducto_lanzaExceptionSiCategoriaNoExiste() {
        when(catRepo.findById(5L)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class,
                () -> service.editarProducto(2L, new ProductoRequestDTO("Polera", "Descripcion", 14990, "M", 10, 5L)));
    }

    @Test
    @DisplayName("eliminarProducto() debe eliminar producto cuando existe")
    void eliminarProducto_debeEliminarProducto() {
        when(prodRepo.existsById(3L)).thenReturn(true);

        service.eliminarProducto(3L);

        org.mockito.Mockito.verify(prodRepo).deleteById(3L);
    }

    @Test
    @DisplayName("eliminarProducto() lanza ElementoNoEncontradoException cuando no existe")
    void eliminarProducto_lanzaExceptionSiNoExiste() {
        when(prodRepo.existsById(100L)).thenReturn(false);

        assertThrows(ElementoNoEncontradoException.class, () -> service.eliminarProducto(100L));
    }

    @Test
    @DisplayName("listarProductos() debe retornar productos listados")
    void listarProductos_debeRetornarProductosListados() {
        when(prodRepo.findAll()).thenReturn(List.of(producto));

        List<ProductoListaResponseDTO> resultado = service.listarProductos();
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("verProducto() debe retornar producto cuando existe")
    void verProducto_debeRetornarProductoCuandoExiste() {
        when(prodRepo.findById(1L)).thenReturn(Optional.of(producto));

        ProductoResponseDTO resultado = service.verProducto(1L);

        assertEquals("Polera", resultado.getNombre());
    }

    @Test
    @DisplayName("verProducto() lanza ElementoNoEncontradoException cuando no existe")
    void verProducto_lanzaExceptionSiNoExiste() {
        when(prodRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class, () -> service.verProducto(99L));
    }

    @Test
    @DisplayName("buscarProducto() debe retornar productos que coinciden con el nombre")
    void buscarProducto_debeRetornarProductosCoincidentes() {
        when(prodRepo.buscarProducto("Pol")).thenReturn(List.of(producto));

        assertEquals(1, service.buscarProducto("Pol").size());
    }

    @Test
    @DisplayName("listarPorCategoria() debe retornar productos de una categoria existente")
    void listarPorCategoria_debeRetornarProductosPorCategoria() {
        when(catRepo.findById(1L)).thenReturn(Optional.of(categoria));
        when(prodRepo.findByCategoria(categoria)).thenReturn(List.of(producto));

        assertEquals(1, service.listarPorCategoria(1L).size());
    }

    @Test
    @DisplayName("obtenerProductosPorIds() debe retornar productos por IDs")
    void obtenerProductosPorIds_debeRetornarProductosPorIds() {
        when(prodRepo.findAllById(List.of(1L, 2L))).thenReturn(List.of(producto));

        assertEquals(1, service.obtenerProductosPorIds(List.of(1L, 2L)).size());
    }

    @Test
    @DisplayName("enviarAlCarrito() debe retornar dto de carrito cuando existe el producto")
    void enviarAlCarrito_debeRetornarDtoCuandoProductoExiste() {
        when(prodRepo.findById(1L)).thenReturn(Optional.of(producto));

        assertEquals("Polera", service.enviarAlCarrito(1L).getNombre());
    }
}
