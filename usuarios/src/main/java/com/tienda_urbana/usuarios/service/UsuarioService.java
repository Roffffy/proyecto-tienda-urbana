package com.tienda_urbana.usuarios.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.tienda_urbana.usuarios.client.CarritoClient;
import com.tienda_urbana.usuarios.dto.CambioContraseniaRequestDTO;
import com.tienda_urbana.usuarios.dto.CambioEmailRequestDTO;
import com.tienda_urbana.usuarios.dto.CreacionUsuarioRequestDTO;
import com.tienda_urbana.usuarios.dto.VisualizarDatosUsuarioResponseDTO;
import com.tienda_urbana.usuarios.exception.ContraseñaNoCoincideException;
import com.tienda_urbana.usuarios.exception.CorreoYaRegistradoException;
import com.tienda_urbana.usuarios.exception.ElementoNoEncontradoException;
import com.tienda_urbana.usuarios.model.Usuario;
import com.tienda_urbana.usuarios.repo.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repo;
    private final CarritoClient cliente;

    private VisualizarDatosUsuarioResponseDTO mapToDto(Usuario usuario) {
        return new VisualizarDatosUsuarioResponseDTO(usuario.getNombre(), usuario.getEmail(),
                usuario.getClaveRecuperacion(), usuario.getFechaCreacion());
    }

    public VisualizarDatosUsuarioResponseDTO crearUsuario(CreacionUsuarioRequestDTO dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new CorreoYaRegistradoException(dto.getEmail());
        }

        Usuario usuario = repo.save(new Usuario(null,dto.getNombre(), dto.getEmail(), dto.getContraseña(),
                dto.getClaveRecuperacion(), "Cliente", LocalDate.now()));

        //cliente.crearCarrito(usuario.getId()); cambiarlo

        return mapToDto(usuario);
    }

    public VisualizarDatosUsuarioResponseDTO verDatosUsuarioPorId(Long id){
        return repo.findById(id).map(usuario -> mapToDto(usuario)).orElseThrow(() -> new ElementoNoEncontradoException("Usuario", id));
    }

    public void cambiarContraseña(Long id, CambioContraseniaRequestDTO dto){
        Usuario usuario = repo.findById(id).orElseThrow(() -> new ElementoNoEncontradoException("Usuario", id));
        if (!dto.getContraseñaAntigua().equals(usuario.getContraseña())) {
            throw new ContraseñaNoCoincideException(); //Crear exception 
        }
        usuario.setContraseña(dto.getContraseñaNueva());
        repo.save(usuario);
    }

    public VisualizarDatosUsuarioResponseDTO cambiarEmail(Long id, CambioEmailRequestDTO dto){
        Usuario usuario = repo.findById(id).orElseThrow(() -> new ElementoNoEncontradoException("Usuario", id));
        if (repo.existsByEmail(dto.getNuevoEmail())) {
            throw new CorreoYaRegistradoException(dto.getNuevoEmail());
        }
        usuario.setEmail(dto.getNuevoEmail());
        return mapToDto(repo.save(usuario));
    }

    //Falta metodo para eliminar | consumir deleteMappging de carrito para eliminarlo tambien
}
