package com.tienda_urbana.usuarios.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tienda_urbana.usuarios.dto.CambioContraseniaRequestDTO;
import com.tienda_urbana.usuarios.dto.CambioEmailRequestDTO;
import com.tienda_urbana.usuarios.dto.CreacionUsuarioRequestDTO;
import com.tienda_urbana.usuarios.dto.VisualizarDatosUsuarioResponseDTO;
import com.tienda_urbana.usuarios.model.Usuario;
import com.tienda_urbana.usuarios.repo.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repo;

    private VisualizarDatosUsuarioResponseDTO mapToDto(Usuario usuario) {
        return new VisualizarDatosUsuarioResponseDTO(usuario.getNombre(), usuario.getEmail(),
                usuario.getClaveRecuperacion(), usuario.getFechaCreacion());
    }

    public VisualizarDatosUsuarioResponseDTO crearUsuario(CreacionUsuarioRequestDTO dto) {
        return mapToDto(repo.save(new Usuario(null, dto.getNombre(), dto.getEmail(), dto.getContraseña(),
                dto.getClaveRecuperacion(), "Cliente", LocalDate.now())));
    }

    public VisualizarDatosUsuarioResponseDTO verDatosUsuarioPorId(Long id) {
        Usuario usuario = repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mapToDto(usuario);
    }

    public String cambiarContraseña(CambioContraseniaRequestDTO dto, Long usuarioId) {
        Usuario usuario = repo.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuaro no encontrado"));
        if (!usuario.getContraseña().equals(dto.getContraseñaAntigua())) {
            return "La contraseña antigua no coincide";
        }
        usuario.setContraseña(dto.getContraseñaNueva());
        ;
        repo.save(usuario);
        return "Contraseña cambiada con exito";
    }

    public String cambiarEmail(CambioEmailRequestDTO dto, Long usuarioId) {
        Usuario usuario = repo.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuaro no encontrado"));
        usuario.setEmail(dto.getNuevoEmail());
        repo.save(usuario);
        return "Email cambiado con exito";
    }
}
