package com.tienda_urbana.catalogo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda_urbana.catalogo.dto.ProductoListaResponseDTO;
import com.tienda_urbana.catalogo.dto.ProductoRequestDTO;
import com.tienda_urbana.catalogo.dto.ProductoResponseDTO;
import com.tienda_urbana.catalogo.service.ProductoService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoContoller.class)
@DisplayName("Test del controlador de productos con MockMvc")
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("POST api/productos debe retornar 201 con producto creado")
    void crearProducto_debeRetornar201ConProductoCreado() throws Exception {
        ProductoRequestDTO request = new ProductoRequestDTO("Polera", "Descripcion", 14990, "M", 10, 1L);
        ProductoResponseDTO response = new ProductoResponseDTO(1L, "Polera", "Descripcion", 14990, "M", 10, "Poleras");

        when(service.crearProducto(any(ProductoRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Polera"));
    }

    @Test
    @DisplayName("PUT api/productos/{id} debe retornar 200 y producto editado")
    void editarProducto_debeRetornar200YProductoEditado() throws Exception {
        ProductoRequestDTO request = new ProductoRequestDTO("Polera Actualizada", "Nueva descripcion", 15990, "L", 5, 1L);
        ProductoResponseDTO response = new ProductoResponseDTO(2L, "Polera Actualizada", "Nueva descripcion", 15990, "L", 5, "Poleras");

        when(service.editarProducto(2L, request)).thenReturn(response);

        mockMvc.perform(put("/api/productos/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.categoria").value("Poleras"));
    }

    @Test
    @DisplayName("GET api/productos debe retornar 200 y lista de productos")
    void listarProductos_debeRetornar200YListaProductos() throws Exception {
        when(service.listarProductos()).thenReturn(List.of(new ProductoListaResponseDTO("Jeans", 34990, "Pantalones")));

        mockMvc.perform(get("/api/productos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Jeans"));
    }

    @Test
    @DisplayName("GET api/productos/{id} debe retornar 200 y producto por id")
    void verProducto_debeRetornar200YProductoPorId() throws Exception {
        when(service.verProducto(3L)).thenReturn(new ProductoResponseDTO(3L, "Chaqueta", "Chaqueta negra", 39990, "XL", 7, "Chaquetas"));

        mockMvc.perform(get("/api/productos/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Chaqueta"));
    }

    @Test
    @DisplayName("DELETE api/productos/{id} debe retornar 200 y mensaje de exito")
    void eliminarProducto_debeRetornar200YMensajeExito() throws Exception {
        mockMvc.perform(delete("/api/productos/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado con exito"));
    }
}
