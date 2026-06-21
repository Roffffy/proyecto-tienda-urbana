package com.tienda_urbana.usuarios.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.tienda_urbana.usuarios.dto.CambioContraseniaRequestDTO;
import com.tienda_urbana.usuarios.dto.CambioEmailRequestDTO;
import com.tienda_urbana.usuarios.dto.CreacionUsuarioRequestDTO;
import com.tienda_urbana.usuarios.dto.VisualizarDatosUsuarioResponseDTO;
import com.tienda_urbana.usuarios.service.UsuarioService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(UsuarioController.class)
@DisplayName("Test del controlador de usuarios con MockMvc")
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService service;

    private final ObjectMapper objectMapper = new ObjectMapper();
    

    @Test
    @DisplayName("POST api/usuarios debe retornar 201 con datos del usuario creado")
    void crearUsuario_debeRetornar201ConDatosUsuario() throws Exception {
        CreacionUsuarioRequestDTO request = new CreacionUsuarioRequestDTO("Nicolas Gutierrez", "nico.gutierrezp@duocuc.cl", "contraNico123.", null);
        VisualizarDatosUsuarioResponseDTO response = new VisualizarDatosUsuarioResponseDTO(request.getNombre(), request.getEmail(), request.getClaveRecuperacion(), LocalDate.now());

        when(service.crearUsuario(any(CreacionUsuarioRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/usuarios").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.email").value(response.getEmail()));
    }

    @Test
    @DisplayName("GET api/usuarios/mi-cuenta/{id} debe retornar 200 y los datos del usuario")
    void verDatosUsuarioPorId_debeRetornar200YdatosDeUsuario() throws Exception {
        VisualizarDatosUsuarioResponseDTO dto = new VisualizarDatosUsuarioResponseDTO("nicolas", "nicolas@gmail.com", null, LocalDate.now());

        when(service.verDatosUsuarioPorId(1L)).thenReturn(dto);

        mockMvc.perform(get("/api/usuarios/mi-cuenta/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.email").value(dto.getEmail()))
            .andExpect(jsonPath("$.nombre").value(dto.getNombre()));
    }

    @Test
    @DisplayName("PUT api/usuarios/mi-cuenta/modificar-contraseña/{id} debe retornar 200 y texto de exito")
    void cambiarContraseña_debeRetornar200YtextoDeExito() throws Exception {
        CambioContraseniaRequestDTO request = new CambioContraseniaRequestDTO("contraAntigua", "contraNueva");

        doNothing().when(service).cambiarContraseña(1L, request);

        mockMvc.perform(put("/api/usuarios/mi-cuenta/modificar-contraseña/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(content().string("Contraseña modificada con exito."));
    }

    @Test
    @DisplayName("PUT api/usuarios/mi-cuenta/modificar-email/{id} debe retornar 200 y datos de usuario")
    void cambiarEmail_debeRetornar200YdatosDeUsuario() throws Exception {
        CambioEmailRequestDTO requestDTO = new CambioEmailRequestDTO("nuevoEmail@gmail.com");
        VisualizarDatosUsuarioResponseDTO response = new VisualizarDatosUsuarioResponseDTO("nicolas", requestDTO.getNuevoEmail(), null, LocalDate.now());
        when(service.cambiarEmail(1L, requestDTO)).thenReturn(response);
        
        mockMvc.perform(put("/api/usuarios/mi-cuenta/modificar-email/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDTO)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.email").value(requestDTO.getNuevoEmail()));
     
    }

}
