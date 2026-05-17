package com.tienda_urbana.usuarios.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

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

    public VisualizarDatosUsuarioResponseDTO verDatosUsuarioPorId(Long id){
        return repo.findById(id).map(usuario -> mapToDto(usuario)).orElseThrow(() -> new NoSuchElementException("El usuario con ID: \" + id + \" no existe."));
    }

    public void cambiarContraseña(Long id, CambioContraseniaRequestDTO dto){
        Usuario usuario = repo.findById(id).orElseThrow(() -> new NoSuchElementException("El usuario con ID: " + id + " no existe."));  
        if (!dto.getContraseñaAntigua().equals(usuario.getContraseña())) {
            throw new IllegalArgumentException("La contraseña antigua no coincide");
        }
        usuario.setContraseña(dto.getContraseñaNueva());
        repo.save(usuario);
    }

    public VisualizarDatosUsuarioResponseDTO cambiarEmail(Long id, CambioEmailRequestDTO dto){
        Usuario usuario = repo.findById(id).orElseThrow(() -> new NoSuchElementException("El usuario con ID: " + id + " no existe."));
        usuario.setEmail(dto.getNuevoEmail());
        return mapToDto(repo.save(usuario));
    }
}
