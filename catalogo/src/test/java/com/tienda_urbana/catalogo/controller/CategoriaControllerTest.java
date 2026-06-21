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
import com.tienda_urbana.catalogo.dto.CategoriaRequestDTO;
import com.tienda_urbana.catalogo.dto.CategoriaResponseDTO;
import com.tienda_urbana.catalogo.service.CategoriaService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoriaController.class)
@DisplayName("Test del controlador de categorias con MockMvc")
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoriaService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("POST api/categorias debe retornar 201 con categoria creada")
    void crearCategoria_debeRetornar201ConCategoriaCreada() throws Exception {
        CategoriaRequestDTO request = new CategoriaRequestDTO("Poleras");
        CategoriaResponseDTO response = new CategoriaResponseDTO("Poleras");

        when(service.crearCategoria(any(CategoriaRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/categorias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Poleras"));
    }

    @Test
    @DisplayName("PUT api/categorias/{id} debe retornar 200 y categoria editada")
    void editarCategoria_debeRetornar200YCategoriaEditada() throws Exception {
        CategoriaRequestDTO request = new CategoriaRequestDTO("Camisas");
        CategoriaResponseDTO response = new CategoriaResponseDTO("Camisas");

        when(service.editarCategoria(1L, request)).thenReturn(response);

        mockMvc.perform(put("/api/categorias/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Camisas"));
    }

    @Test
    @DisplayName("GET api/categorias debe retornar 200 y lista de categorias")
    void verCategorias_debeRetornar200YListaCategorias() throws Exception {
        when(service.verCategorias()).thenReturn(List.of(new CategoriaResponseDTO("Chaquetas")));

        mockMvc.perform(get("/api/categorias")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Chaquetas"));
    }

    @Test
    @DisplayName("GET api/categorias/buscar/{nombre} debe retornar 200 y categorias encontradas")
    void buscarCategoria_debeRetornar200YCategoriasEncontradas() throws Exception {
        when(service.buscarCategoria("Pol")).thenReturn(List.of(new CategoriaResponseDTO("Poleras")));

        mockMvc.perform(get("/api/categorias/buscar/Pol")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Poleras"));
    }

    @Test
    @DisplayName("DELETE api/categorias/{id} debe retornar 200 y mensaje de exito")
    void eliminarCategoria_debeRetornar200YMensajeExito() throws Exception {
        mockMvc.perform(delete("/api/categorias/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Categoria eliminada con exito"));
    }
}
