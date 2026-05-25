package com.proyecto.resena.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.resena.DTO.ResenaRequestDTO;
import com.proyecto.resena.DTO.ResenaResponseDTO;
import com.proyecto.resena.exception.ResenaNotFoundException;
import com.proyecto.resena.model.Resena;
import com.proyecto.resena.repository.ResenaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResenaService {
    private final ResenaRepository repo;

    private ResenaResponseDTO mapToDTO(Resena r){
        return new ResenaResponseDTO(
            r.getId(),
            r.getClasificacion(),
            r.getComentario(),
            r.getCreadoEn(),
            r.getUsuarioId(),
            r.getProductoId()
        );
    }

    public List<ResenaResponseDTO> obtenerResena(){
        return repo.findAll().stream().map(this::mapToDTO)
        .collect(Collectors.toList());
    }

    public Optional<ResenaResponseDTO> obtenerPorId(Long id){
        if(!repo.existsById(id)){
            throw new ResenaNotFoundException(id);
        }
        return repo.findById(id).map(this::mapToDTO);
    }
    
    public ResenaResponseDTO guardarR(ResenaRequestDTO resena){
        Resena r = new Resena();
            r.setClasificacion(resena.getClasificacion());

            r.setComentario(resena.getComentario());

            r.setCreadoEn(LocalDateTime.now());

            r.setUsuarioId(resena.getUsuarioId());

            r.setProductoId(resena.getProductoId());
        return mapToDTO(repo.save(r)); 
    }

    public void eliminarResena (Long id){
        repo.deleteById(id);
    }


    public Optional<ResenaResponseDTO> actualizarResena(Long id, ResenaRequestDTO dto){
        if(!repo.existsById(id)){
            throw new ResenaNotFoundException(id);
        }

        return repo.findById(id)
        .map(resena ->{

            resena.setClasificacion(dto.getClasificacion());
            resena.setComentario(dto.getComentario());
            resena.setUsuarioId(dto.getUsuarioId());
            resena.setProductoId(dto.getProductoId());

            return mapToDTO(repo.save(resena));
        }); 
    }
}
