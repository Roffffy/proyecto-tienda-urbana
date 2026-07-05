package com.proyecto.devolucion.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.devolucion.DTO.DevolucionRequestDTO;
import com.proyecto.devolucion.DTO.DevolucionResponseDTO;
import com.proyecto.devolucion.controller.DevolucionController;
import com.proyecto.devolucion.service.DevolucionService;

@WebMvcTest(DevolucionController.class)
@DisplayName("test del devolucionControler usando MockMvc")
public class DevolucionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DevolucionService devolucionService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("get api/devolucion debe retornar un Json con la lista de devoluciones y el codigo 200 ")
    void listar_debeRetornar200ConListaDeDevolcuiones() throws Exception{

        DevolucionResponseDTO dto = new DevolucionResponseDTO(1L, "producto roto", "foto1.jpg", "pendiente", "foto1.jpg", LocalDateTime.now(), 11L, 5L);

        when(devolucionService.obtenerDevolucion()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/devoluciones")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].motivo").value("producto roto"))
        .andExpect(jsonPath("$[0].estado").value("pendiente"));
    }

    @Test
    @DisplayName("post api/devoluciones debe retornar el 201 con datos validos")
    void crear_debeRetornar201_conDatosValidos() throws Exception{
        DevolucionResponseDTO response = new DevolucionResponseDTO(1L, "producto roto", "foto2.jpg", "pendiente", "foto2.jpg", LocalDateTime.now(), 15L, 9L); 
        DevolucionRequestDTO request = new DevolucionRequestDTO();
        when(devolucionService.guardarD(any(DevolucionRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/devoluciones").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated()) //HTTP 201
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.motivo").value("producto roto"));
    }

    @Test
    @DisplayName("PUT api/devoluciones/{id} debe retornar el codigo 200 cuando los datos sean validos")
    void actualizarDebeRetornar200cuandoSeaValido() throws Exception{
        DevolucionRequestDTO request = new DevolucionRequestDTO("el producto llega roto","url2","url2",10L,5L);

        DevolucionResponseDTO response = new DevolucionResponseDTO(1L,"el producto llega roto","url2","pendiente","url2",LocalDateTime.now(),10L,5L);

        when(devolucionService.actualizarDevolucion(any(Long.class), any(DevolucionRequestDTO.class))).thenReturn(Optional.of(response));

        mockMvc.perform(put("api/devoluciones/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.motivo").value("el producto llego roto"));
    }

    @Test
    @DisplayName("delete api/devoluciones/{id} debe retornar 204")
    void eliminarDebeRetornar204() throws Exception{
        doNothing().when(devolucionService).eliminarDevolucion(1L);

        mockMvc.perform(delete("api/devoluciones/{id}", 1L))
        .andDo(print())
        .andExpect(status().isNoContent());
    }
}

