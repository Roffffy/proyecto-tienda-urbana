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

    public Optional<VisualizarDatosUsuarioResponseDTO> verDatosUsuarioPorId(Long id) {
        return repo.findById(id).map(usuario -> mapToDto(usuario));
    }

    public Optional<String> cambiarContraseña(CambioContraseniaRequestDTO dto, Long id) {
        return repo.findById(id).map(usuario -> {
            if (dto.getContraseñaAntigua().equals(usuario.getContraseña())) {
                usuario.setContraseña(dto.getContraseñaNueva());
                repo.save(usuario);
                return "Contraseña cambiada con exito";
            } else {
                return "La contraseña antigua no coincide";
            }
        });
    }

    public Optional<String> cambiarEmail(CambioEmailRequestDTO dto, Long usuarioId) {
        return repo.findById(usuarioId).map(usuario -> {
            usuario.setEmail(dto.getNuevoEmail());
            return "Email actualizado con exito";
        });
    }
}
