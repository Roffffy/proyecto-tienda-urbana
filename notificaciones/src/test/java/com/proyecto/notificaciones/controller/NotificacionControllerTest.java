package com.proyecto.notificaciones.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.proyecto.notificaciones.DTO.NotificacionRequestDTO;
import com.proyecto.notificaciones.DTO.NotificacionResponseDTO;
import com.proyecto.notificaciones.service.NotificacionService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(NotificacionController.class)
@DisplayName("tests del productoController con MockMvc")
public class NotificacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private NotificacionService notificacionService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("get api/notificaciones debe retornar un Json con la lista de notificaciones y el codigo 200")
    void listar_debeRetornar200ConListaDeNotificaciones() throws Exception{

        NotificacionResponseDTO dto = new NotificacionResponseDTO();

        when(notificacionService.obtenerNotificacion()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/productos")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].tipo").value("compra"))
        .andExpect(jsonPath("$[0].canal").value("email"));
    }

    @Test
    @DisplayName("POST api/notificaciones debe retorna 201 con datos validos")
    void crear_debeRetornar201_cuandoDatosValidos() throws Exception{

        NotificacionRequestDTO request = new NotificacionRequestDTO();
        NotificacionResponseDTO response = new NotificacionResponseDTO();
        when(notificacionService.guardarN(any(NotificacionRequestDTO.class))).thenReturn(response);


        mockMvc.perform(post("/api/notificaciones").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated()) 
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.tipo").value("Compra"));
                   
    
    }
}
