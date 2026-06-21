package com.tienda_urbana.carrito.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienda_urbana.carrito.dto.AgregarItemRequestDTO;
import com.tienda_urbana.carrito.dto.CarritoResponseDTO;
import com.tienda_urbana.carrito.dto.ProductoDTO;
import com.tienda_urbana.carrito.service.CarritoService;

@WebMvcTest(CarritoController.class)
@DisplayName("Test del controlador de carrito con MockMvc")
public class CarritoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarritoService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("POST api/carrito/{usuarioId} debe retornar 201 con producto agregado")
    void agregarItem_debeRetornar201ConProductoAgregado() throws Exception {
        AgregarItemRequestDTO request = new AgregarItemRequestDTO(1L, 2);
        ProductoDTO response = new ProductoDTO(1L, "Polera", "Poleras", "M", 14990, 2);

        when(service.agregarItem(1L, request)).thenReturn(response);

        mockMvc.perform(post("/api/carrito/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productoId").value(1))
                .andExpect(jsonPath("$.cantidad").value(2));
    }

    @Test
    @DisplayName("GET api/carrito/{usuarioId} debe retornar 200 con items del carrito")
    void verCarrito_debeRetornar200ConItemsDelCarrito() throws Exception {
        CarritoResponseDTO response = new CarritoResponseDTO(
                java.util.List.of(new ProductoDTO(1L, "Polera", "Poleras", "M", 14990, 2)));

        when(service.verCarrito(1L)).thenReturn(response);

        mockMvc.perform(get("/api/carrito/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].nombre").value("Polera"));
    }
}
