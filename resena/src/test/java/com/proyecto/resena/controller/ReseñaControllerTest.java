package com.proyecto.resena.controller;

import static org.mockito.ArgumentMatchers.any;
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.resena.DTO.ResenaRequestDTO;
import com.proyecto.resena.DTO.ResenaResponseDTO;
import com.proyecto.resena.service.ResenaService;

@WebMvcTest(ResenaController.class)
@DisplayName("test de la reseñaController con MockMvc")
public class ReseñaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ResenaService resenaService;

    private final ObjectMapper objectMapper = new ObjectMapper();

     @Test
    @DisplayName("GET api/resenas debe retornar un JSON con la lista de reseñas y el codigo 200")
    void listar_debeRetornar200ConListaDeProductos() throws Exception{
       
        ResenaResponseDTO dto = new ResenaResponseDTO( 1L, 5,"Excelente producto",null,12L,45L);
  
        when(resenaService.obtenerResena()).thenReturn(List.of(dto));
        
        mockMvc.perform(get("/api/resenas")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print()) 
        .andExpect(status().isOk()) 
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].coemntario").value("excelente producto"))
        .andExpect(jsonPath("$[0].clasificacion").value(5));
        
        
    }

    @Test
    @DisplayName("POST api/reseñas debe retorna 201 con datos validos")
    void crear_debeRetornar201_cuandoDatosValidos() throws Exception{

        ResenaRequestDTO request = new ResenaRequestDTO();
        request.setClasificacion(5);
        request.setComentario("Excelente producto");
        request.setUsuarioId(12L);
        request.setProductoId(45L);
        ResenaResponseDTO response = new ResenaResponseDTO(1L,5,"Excelente producto",null,12L,45L);
        when(resenaService.guardarR(any(ResenaRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/resenas").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.").value(""));
                   
    }

    @Test
    @DisplayName("PUT api/resenas/{id} debe retornar el codigo 200 cuando los datos sean validos")
    void actualizarDebeRetornar200cuandoSeaValido() throws Exception{
        ResenaRequestDTO request = new ResenaRequestDTO(5,"excelente producto, lo recomiendo",12L,45L);
        
        ResenaResponseDTO response = new ResenaResponseDTO(1L,2,"me llego roto el producto",LocalDateTime.now(),15L,50L);

        mockMvc.perform(put("api/resenas/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.comentario").value("me llego roto el producto"));
    }

    @Test
    @DisplayName("delete api/resenas/{id} debe retornar 204")
    void eliminarDebeRetornar204() throws Exception{
        doNothing().when(resenaService).eliminarResena(1L);

        mockMvc.perform(delete("api/resenas/{id}", 1L))
        .andDo(print())
        .andExpect(status().isNoContent());
    }
}
