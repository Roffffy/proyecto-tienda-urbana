package com.proyecto.pagos.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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
import com.proyecto.pagos.DTO.PagoRequestDTO;
import com.proyecto.pagos.DTO.PagoResponseDTO;
import com.proyecto.pagos.Service.PagoService;


@WebMvcTest(PagoController.class)
@DisplayName("test del pagoController con MockMvc")
public class PagoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagoService pagoService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("get api/pagos debe retornar un JSON con la lista de productos y el codigo 200")
    void listar_debeRetornar200conListaDePagos() throws Exception{
        PagoResponseDTO dto = new PagoResponseDTO(1L, "BancoEstado", "aprobado", "debito", "BE-93891",new BigDecimal("25000"), LocalDateTime.now(), 1L);

        when(pagoService.obtenerPagos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/pagos")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(print()) 
        .andExpect(status().isOk()) 
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].proveedor").value("bancoestado"))
        .andExpect(jsonPath("$[0].monto").value(25000));
    }

    @Test
    @DisplayName("POST api/productos debe retorna 201 con datos validos")
    void crear_debeRetornar201_cuandoDatosValidos() throws Exception{
        //objetos simulados para envio y respuesta del endpoint
        PagoRequestDTO request = new PagoRequestDTO( "bancoEstado","debito",new BigDecimal("25000"),"ST-1234",10L);
        PagoResponseDTO response = new PagoResponseDTO(1L, "bancoEstado", "aprobado", "debito", "ST-1234", new BigDecimal("25000"), LocalDateTime.now(), 10L);

        when(pagoService.guardarP(any(PagoRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/pagos").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated()) //HTTP 201
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.proveedor").value("bancosantander"));
                   
    
    }

}
