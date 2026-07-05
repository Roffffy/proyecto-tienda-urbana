package com.proyecto.notificaciones.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Test
    @DisplayName("PUT api/notificaciones/{id} debe retornar el codigo 200 cuando los datos sean validos")
    void actualizarDebeRetornar200cuandoSeaValido() throws Exception{
        NotificacionRequestDTO request = new NotificacionRequestDTO("alerta", "email", "su compra fue realizada con exito",15L);

        NotificacionResponseDTO response = new NotificacionResponseDTO(1L, "alerta","email","su compra fue realizada con exito",true,LocalDateTime.now(),15L);

        when(notificacionService.actualizarNotificacion(any(Long.class), any(NotificacionRequestDTO.class))).thenReturn(Optional.of(response));

        mockMvc.perform(put("api/notificaciones/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.canal").value("email"));
    }

    @Test
    @DisplayName("delete api/notificaciones/{id} debe retornar 204")
    void eliminarDebeRetornar204() throws Exception{
        doNothing().when(notificacionService).eliminarNotificacion(1L);

        mockMvc.perform(delete("api/notificaciones/{id}", 1L))
        .andDo(print())
        .andExpect(status().isNoContent());
    }
}
