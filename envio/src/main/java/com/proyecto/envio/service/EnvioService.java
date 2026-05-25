package com.proyecto.envio.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.envio.DTO.EnvioRequestDTO;
import com.proyecto.envio.DTO.EnvioResponseDTO;
import com.proyecto.envio.exception.EnvioNotFoundException;
import com.proyecto.envio.model.Envio;
import com.proyecto.envio.repository.EnvioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnvioService {
    private final EnvioRepository repo;

    private EnvioResponseDTO mapToDTO(Envio e){
        return new EnvioResponseDTO(
            e.getId(),
            e.getDireccion(),
            e.getEstado(),
            e.getEtiquetaUrl(),
            e.getDespachadoEn(),
            e.getEntregadoEn(),
            e.getOrdenId()
        );
    }

    public List<EnvioResponseDTO> obtenerEnvios(){
        return repo.findAll().stream().map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    public Optional<EnvioResponseDTO> obtenerPorId(Long id){
        if(!repo.existsById(id)){
            throw new EnvioNotFoundException(id);
        }
        return repo.findById(id).map(this::mapToDTO);
    }

    public void eliminarEnvio(Long id){
        if(!repo.existsById(id)){
            throw new EnvioNotFoundException(id);
        }
        repo.deleteById(id);
    }

    //obtener solo por orden
    public List<EnvioResponseDTO> obtenerPorOrdenId(Long ordenId){
        return repo.findByOrdenId(ordenId)
        .stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    public EnvioResponseDTO guardarE(EnvioRequestDTO envio){
        Envio e = new Envio();
            e.setDireccion(envio.getDireccion());
            e.setEtiquetaUrl(envio.getEtiquetaUrl());
            e.setEstado(envio.getEstado());
            e.setDespachadoEn(null);
            e.setEntregadoEn(null);
            e.setOrdenId(envio.getOrdenId());
        return mapToDTO(repo.save(e));
    }

    public Optional<EnvioResponseDTO> actualizarEnvio(Long id, EnvioRequestDTO dto){
        return repo.findById(id)
        .map(envio ->{

            envio.setDireccion(dto.getDireccion());
            envio.setEtiquetaUrl(dto.getEtiquetaUrl());
            envio.setEstado(dto.getEstado());
            envio.setDespachadoEn(dto.getDespachadoEn());
            envio.setEntregadoEn(dto.getEntregadoEn());
            envio.setOrdenId(dto.getOrdenId());

            return mapToDTO(repo.save(envio));
        });
    }
}
