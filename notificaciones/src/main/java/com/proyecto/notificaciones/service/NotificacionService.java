package com.proyecto.notificaciones.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.notificaciones.DTO.NotificacionRequestDTO;
import com.proyecto.notificaciones.DTO.NotificacionResponseDTO;
import com.proyecto.notificaciones.Exception.NotificacionNotFoundException;
import com.proyecto.notificaciones.model.Notificacion;
import com.proyecto.notificaciones.repository.NotificacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificacionService {
    private final NotificacionRepository repo;

    //para los valores de boolean son necesarios is y no get
    private NotificacionResponseDTO mapToDTO(Notificacion n){
        return new NotificacionResponseDTO(

            n.getId(),
            n.getTipo(),
            n.getCanal(),
            n.getMensaje(),
            n.isEnviado(),
            n.getEnviadoEn(),
            n.getUsuarioId()
        );
    }

    public List<NotificacionResponseDTO> obtenerNotificacion(){
        return repo.findAll().stream().map(this::mapToDTO)
        .collect(Collectors.toList());
    }

     public Optional<NotificacionResponseDTO> obtenerPorId(Long id){
        if(!repo.existsById(id)){
            throw new NotificacionNotFoundException(id);
        }
        return repo.findById(id).map(this::mapToDTO);
    }

    public NotificacionResponseDTO guardarN(NotificacionRequestDTO notificacion){
        Notificacion n = new Notificacion();

            n.setTipo(notificacion.getTipo());
            n.setCanal(notificacion.getCanal());
            n.setMensaje(notificacion.getMensaje());
            n.setEnviadoEn(null);
            n.setEnviado(false);
            n.setUsuarioId(notificacion.getUsuarioId());
        return mapToDTO(repo.save(n));
    }

    public void eliminarNotificacion(Long id){
        repo.deleteById(id);
    }

    public Optional<NotificacionResponseDTO> actualizarNotificacion(Long id, NotificacionRequestDTO dto){
        return repo.findById(id)
        .map(notificacion ->{

            notificacion.setTipo(dto.getTipo());
            notificacion.setCanal(dto.getCanal());
            notificacion.setMensaje(dto.getMensaje());
            notificacion.setUsuarioId(dto.getUsuarioId());

            return mapToDTO(repo.save(notificacion));
        });
    }
}
