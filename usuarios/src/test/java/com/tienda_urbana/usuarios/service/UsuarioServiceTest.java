package com.tienda_urbana.usuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tienda_urbana.usuarios.dto.CambioContraseniaRequestDTO;
import com.tienda_urbana.usuarios.dto.CambioEmailRequestDTO;
import com.tienda_urbana.usuarios.dto.CreacionUsuarioRequestDTO;
import com.tienda_urbana.usuarios.dto.VisualizarDatosUsuarioResponseDTO;
import com.tienda_urbana.usuarios.model.Usuario;
import com.tienda_urbana.usuarios.repo.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test del servicio de usuarios en memoria")
public class UsuarioServiceTest {


    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioService service;

    private Usuario nuevoUsuario;
    private Usuario usuarioGuardado;
    private CreacionUsuarioRequestDTO requestDto;
    private VisualizarDatosUsuarioResponseDTO responseDto;

    @BeforeEach
    void setUp(){
        nuevoUsuario = new Usuario(null, "Nicolas Gutierrez", "nico.gutierrezp@duocuc.cl", "ContraNico.123", null, "Cliente", LocalDate.now());
        usuarioGuardado = new Usuario(1L, nuevoUsuario.getNombre(), nuevoUsuario.getEmail(), nuevoUsuario.getContrasena(), nuevoUsuario.getClaveRecuperacion(), "Cliente", LocalDate.now());
        requestDto = new CreacionUsuarioRequestDTO(nuevoUsuario.getNombre(), nuevoUsuario.getEmail(), nuevoUsuario.getContrasena(), nuevoUsuario.getClaveRecuperacion());
        responseDto = new VisualizarDatosUsuarioResponseDTO(usuarioGuardado.getNombre(), usuarioGuardado.getEmail(), usuarioGuardado.getClaveRecuperacion(), usuarioGuardado.getFechaCreacion());

        

    }

    @Test
    @DisplayName("crearUsuario() debe guardar el usuario en el repositorio y retornarlo")
    void crearUsuario_debeGuardarYretornarUsuario(){
        when(repo.save(any(Usuario.class))).thenReturn(usuarioGuardado);

        VisualizarDatosUsuarioResponseDTO resultado = service.crearUsuario(requestDto);

        assertNotNull(resultado);
        assertEquals(responseDto,resultado);
        verify(repo, times(1)).save(any(Usuario.class));
    }

    @Test
    @DisplayName("verDatosUsuarioPorId() debe retornar dto con datos de usuario")
    void verDatosUsuarioPorId_debeRetornarDatosUsuario(){
        when(repo.findById(usuarioGuardado.getId())).thenReturn(Optional.of(usuarioGuardado));

        VisualizarDatosUsuarioResponseDTO resultado = service.verDatosUsuarioPorId(usuarioGuardado.getId());
        assertNotNull(resultado);
        assertEquals(responseDto, resultado);
    }

    @Test
    @DisplayName("cambiarContraseña() no debe retornar nada solo cambiar contraseña")
    void cambiarContraseña_noRetornaNada(){
        when(repo.save(usuarioGuardado)).thenReturn(usuarioGuardado);

        when(repo.findById(usuarioGuardado.getId())).thenReturn(Optional.of(usuarioGuardado));

        service.cambiarContraseña(usuarioGuardado.getId(), new CambioContraseniaRequestDTO(usuarioGuardado.getContrasena(),"contraNueva"));
        
        assertEquals("contraNueva", usuarioGuardado.getContrasena());
        
    }

    @Test
    @DisplayName("cambiarEmail() debe retornar datos del usuario con nuevo email")
    void cambiarEmail_debeRetornarDatosUsusario(){
        when(repo.save(usuarioGuardado)).thenReturn(usuarioGuardado);

        when(repo.findById(usuarioGuardado.getId())).thenReturn(Optional.of(usuarioGuardado));

        service.cambiarEmail(usuarioGuardado.getId(), new CambioEmailRequestDTO("nuevoEmail@gmail.com"));

        assertEquals("nuevoEmail@gmail.com", usuarioGuardado.getEmail());
    }

}
