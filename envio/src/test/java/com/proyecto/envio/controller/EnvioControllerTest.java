package com.proyecto.envio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.envio.DTO.EnvioRequestDTO;
import com.proyecto.envio.DTO.EnvioResponseDTO;
import com.proyecto.envio.service.EnvioService;

@WebMvcTest(EnvioController.class)
@DisplayName("test del EnvioController con MockMvc")
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EnvioService envioService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("get api/envios debe retornar un Json con la lista de productos y el codigo 200")
    void listar_debeRetornar200conListaDeProductos() throws Exception{

        EnvioResponseDTO dto = new EnvioResponseDTO(1L, "santiago centro", "pendiente", "url2", null, null, 1L);
        when(envioService.obtenerEnvios()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/envios")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print()) 
        .andExpect(status().isOk()) 
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].direccion").value("santiago centro"))
        .andExpect(jsonPath("$[0].estado").value("pendiente"));
    }

    @Test
    @DisplayName("post api/envios debe retornar 201 con datos validos")
    void crear_debeRetornar201_cuandoDatosValidos() throws Exception{

        EnvioRequestDTO request = new EnvioRequestDTO();
        request.setDireccion("melipilla");
        request.setEstado("atrasado");
        request.setEtiquetaUrl("url2");
        request.setOrdenId(2L);
        EnvioResponseDTO response = new EnvioResponseDTO(1L, "milipilla","atrasado", "url2",null, null, 2L);
        when(envioService.guardarE(any(EnvioRequestDTO.class))).thenReturn(response);

         mockMvc.perform(post("/api/envios").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated()) //HTTP 201
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.direccion").value("melipilla"))
        .andExpect(jsonPath("$.estado").value("atrasado"));
    }

}
