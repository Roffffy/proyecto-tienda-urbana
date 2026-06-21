package com.tienda_urbana.ordenes.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tienda_urbana.ordenes.dto.CarritoItemDTO;
import com.tienda_urbana.ordenes.dto.OrdenItemResponseDTO;
import com.tienda_urbana.ordenes.dto.OrdenResponseDTO;
import com.tienda_urbana.ordenes.service.OrdenService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrdenController.class)
@DisplayName("Test del controlador de órdenes con MockMvc")
public class OrdenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrdenService service;

    @Test
    @DisplayName("POST /api/ordenes/carrito/{carritoId}/usuario/{usuarioId} debe retornar 201")
    void crearOrdenDeCarrito_debeRetornar201() throws Exception {
        OrdenItemResponseDTO items = new OrdenItemResponseDTO(Arrays.asList(
            new CarritoItemDTO(1L, "Polera", 14990, 2)
        ));
        OrdenResponseDTO response = new OrdenResponseDTO(1L, LocalDateTime.now(), 29980, "Procesando", items);

        when(service.crearOrdenDeCarrito(1L, 1L)).thenReturn(response);

        mockMvc.perform(post("/api/ordenes/carrito/1/usuario/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ordenId").value(1))
                .andExpect(jsonPath("$.estado").value("Procesando"));
    }

    @Test
    @DisplayName("GET /api/ordenes/orden/{ordenId} debe retornar 200")
    void verOrden_debeRetornar200() throws Exception {
        OrdenItemResponseDTO items = new OrdenItemResponseDTO(Arrays.asList(
            new CarritoItemDTO(1L, "Polera", 14990, 2)
        ));
        OrdenResponseDTO response = new OrdenResponseDTO(2L, LocalDateTime.now(), 29980, "Completada", items);

        when(service.verOrden(2L)).thenReturn(response);

        mockMvc.perform(get("/api/ordenes/orden/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ordenId").value(2))
                .andExpect(jsonPath("$.total").value(29980));
    }

    @Test
    @DisplayName("GET /api/ordenes/{id} (test endpoint) debe retornar items")
    void verCarrito_debeRetornar200() throws Exception {
        OrdenItemResponseDTO items = new OrdenItemResponseDTO(Arrays.asList(
            new CarritoItemDTO(1L, "Polera", 14990, 1)
        ));

        when(service.verCarrito(anyLong())).thenReturn(items);

        mockMvc.perform(get("/api/ordenes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].nombre").value("Polera"));
    }
}
