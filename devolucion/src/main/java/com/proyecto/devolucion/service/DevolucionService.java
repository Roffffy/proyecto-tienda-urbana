package com.proyecto.devolucion.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.devolucion.DTO.DevolucionRequestDTO;
import com.proyecto.devolucion.DTO.DevolucionResponseDTO;
import com.proyecto.devolucion.Exception.DevolucionNotFoundExcepcion;
import com.proyecto.devolucion.model.Devolucion;
import com.proyecto.devolucion.repository.DevolucionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DevolucionService {

    private final DevolucionRepository repo;

    private DevolucionResponseDTO mapToDTO(Devolucion d){
        return new DevolucionResponseDTO(
            d.getId(),
            d.getMotivo(),
            d.getFotoEnviadaUrl(),
            d.getEstado(),
            d.getEtiquetaRetornoUrl(),
            d.getSolicitadoEn(),
            d.getOrdenId(),
            d.getUsuarioId()
        );
    }

    public List<DevolucionResponseDTO> obtenerDevolucion(){
        return repo.findAll().stream().map(this::mapToDTO)
        .collect(Collectors.toList());
    } 

    public Optional<DevolucionResponseDTO> obtenerPorId(Long id){
        if(!repo.existsById(id)){
            throw new DevolucionNotFoundExcepcion(id);
        }
        return repo.findById(id).map(this::mapToDTO);
    }

    public DevolucionResponseDTO guardarD(DevolucionRequestDTO devolucion){
        Devolucion d = new Devolucion();
            d.setMotivo(devolucion.getMotivo());
            d.setFotoEnviadaUrl(devolucion.getFotoEnviadaUrl());
            d.setEstado("pendiente");
            d.setEtiquetaRetornoUrl(devolucion.getEtiquetaRetornoUrl());
            d.setSolicitadoEn(LocalDateTime.now());
            d.setOrdenId(devolucion.getOrdenId());
            d.setUsuarioId(devolucion.getUsuarioId());
        return mapToDTO(repo.save(d));
    }

    public void eliminarDevolucion(Long id){
        repo.deleteById(id);
    }

    public Optional<DevolucionResponseDTO> actualizarDevolucion(Long id, DevolucionRequestDTO dto){
        return repo.findById(id)
        .map(devolucion ->{
            devolucion.setMotivo(dto.getMotivo());
            devolucion.setFotoEnviadaUrl(dto.getFotoEnviadaUrl());
            devolucion.setEtiquetaRetornoUrl(dto.getEtiquetaRetornoUrl());

            return mapToDTO(repo.save(devolucion));
        });
    }

    //actualizacion mas especifica, solo papara pendiente y otro para aprobado
    public Optional<DevolucionResponseDTO> actualizarEstado(Long id, String estado){
        return repo.findById(id).map(devolucion ->{
            devolucion.setEstado(estado);

            return mapToDTO(repo.save(devolucion));
        });
    }

}
