package com.tienda_urbana.catalogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.tienda_urbana.catalogo.dto.CategoriaRequestDTO;
import com.tienda_urbana.catalogo.dto.CategoriaResponseDTO;
import com.tienda_urbana.catalogo.exception.CategoriaYaExistenteException;
import com.tienda_urbana.catalogo.exception.ElementoNoEncontradoException;
import com.tienda_urbana.catalogo.exception.SinCategoriaException;
import com.tienda_urbana.catalogo.model.Categoria;
import com.tienda_urbana.catalogo.repository.CategoriaRepository;
import com.tienda_urbana.catalogo.repository.ProductoRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test del servicio de categorias en memoria")
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository repo;

    @Mock
    private ProductoRepository prodRepo;

    @InjectMocks
    private CategoriaService service;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria(1L, "Poleras");
    }

    @Test
    @DisplayName("crearCategoria() debe guardar una categoria nueva")
    void crearCategoria_debeGuardarCategoriaNueva() {
        when(repo.existsByNombre("Poleras")).thenReturn(false);
        when(repo.save(any(Categoria.class))).thenReturn(categoria);

        CategoriaResponseDTO resultado = service.crearCategoria(new CategoriaRequestDTO("Poleras"));

        assertEquals("Poleras", resultado.getNombre());
        verify(repo, times(1)).save(any(Categoria.class));
    }

    @Test
    @DisplayName("crearCategoria() lanza CategoriaYaExistenteException cuando existe la categoria")
    void crearCategoria_lanzaExceptionSiExiste() {
        when(repo.existsByNombre("Poleras")).thenReturn(true);

        assertThrows(CategoriaYaExistenteException.class,
                () -> service.crearCategoria(new CategoriaRequestDTO("Poleras")));
    }

    @Test
    @DisplayName("editarCategoria() debe actualizar el nombre cuando existe")
    void editarCategoria_debeActualizarNombre() {
        Categoria existing = new Categoria(1L, "Poleras");
        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.existsByNombre("Camisas")).thenReturn(false);
        when(repo.save(any(Categoria.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CategoriaResponseDTO resultado = service.editarCategoria(1L, new CategoriaRequestDTO("Camisas"));

        assertEquals("Camisas", resultado.getNombre());
    }

    @Test
    @DisplayName("editarCategoria() lanza ElementoNoEncontradoException cuando no existe")
    void editarCategoria_lanzaExceptionSiNoExiste() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ElementoNoEncontradoException.class,
                () -> service.editarCategoria(99L, new CategoriaRequestDTO("Camisas")));
    }

    @Test
    @DisplayName("verCategorias() debe retornar todas las categorias")
    void verCategorias_debeRetornarTodasLasCategorias() {
        when(repo.findAll()).thenReturn(List.of(categoria));

        assertEquals(1, service.verCategorias().size());
        assertEquals("Poleras", service.verCategorias().get(0).getNombre());
    }

    @Test
    @DisplayName("buscarCategoria() debe retornar categorias que coinciden por nombre")
    void buscarCategoria_debeRetornarCategoriasPorNombre() {
        when(repo.buscarCategoria("Pol")).thenReturn(List.of(categoria));

        assertEquals(1, service.buscarCategoria("Pol").size());
    }

    @Test
    @DisplayName("eliminarCategoria() debe eliminar categoria no critica")
    void eliminarCategoria_debeEliminarCategoriaNoCritica() {
        when(repo.existsById(2L)).thenReturn(true);
        doNothing().when(prodRepo).reasignarCategoria(2L);

        service.eliminarCategoria(2L);

        verify(prodRepo, times(1)).reasignarCategoria(2L);
        verify(repo, times(1)).deleteById(2L);
    }

    @Test
    @DisplayName("eliminarCategoria() lanza ElementoNoEncontradoException cuando no existe")
    void eliminarCategoria_lanzaExceptionSiNoExiste() {
        when(repo.existsById(100L)).thenReturn(false);

        assertThrows(ElementoNoEncontradoException.class, () -> service.eliminarCategoria(100L));
    }

    @Test
    @DisplayName("eliminarCategoria() lanza SinCategoriaException para ID 1 critico")
    void eliminarCategoria_lanzaSinCategoriaExceptionParaId1() {
        when(repo.existsById(1L)).thenReturn(true);

        assertThrows(SinCategoriaException.class, () -> service.eliminarCategoria(1L));
    }
}
