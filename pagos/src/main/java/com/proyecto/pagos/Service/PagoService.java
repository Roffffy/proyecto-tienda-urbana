package com.proyecto.pagos.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proyecto.pagos.DTO.PagoRequestDTO;
import com.proyecto.pagos.DTO.PagoResponseDTO;
import com.proyecto.pagos.Exception.PagoNotFoundException;
import com.proyecto.pagos.Model.Pagos;
import com.proyecto.pagos.Repository.PagoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository repo;

    private PagoResponseDTO mapToDTO(Pagos p){
        return new PagoResponseDTO(
            p.getId(),
            p.getProveedor(),
            p.getEstado(),
            p.getMetodoPago(),
            p.getReferenciaExterna(),
            p.getMonto(),
            p.getProcesadoEn(),
            p.getOrdenId()
        );
    }

    public List<PagoResponseDTO> obtenerPagos(){
       return repo.findAll().stream().map(this::mapToDTO)
       .collect(Collectors.toList());
    }

    public Optional<PagoResponseDTO> obtenerPorId(Long id){
        if(!repo.existsById(id)){
            throw new PagoNotFoundException(id);
        }
        return repo.findById(id).map(this::mapToDTO);
    }

    public PagoResponseDTO guardarP(PagoRequestDTO pagos){
        Pagos p = new Pagos();
        p.setProveedor(pagos.getProveedor());
        p.setEstado("Procesando");
        p.setMetodoPago(pagos.getMetodoPago());
        p.setReferenciaExterna(pagos.getReferenciaExterna());
        p.setMonto(pagos.getMonto());
        p.setProcesadoEn(LocalDateTime.now());
        p.setOrdenId(pagos.getOrdenId());
        return mapToDTO(repo.save(p));
    }

    public void eliminarPago(Long id){
        if(!repo.existsById(id)){
            throw new PagoNotFoundException(id);
        }
        repo.deleteById(id);
    }

    public Optional<PagoResponseDTO> actualizarPago(Long id, PagoRequestDTO dto){
        return repo.findById(id)
        .map(pagos ->{
            pagos.setProveedor(dto.getProveedor());
            pagos.setMetodoPago(dto.getMetodoPago());
            pagos.setMonto(dto.getMonto());
            pagos.setReferenciaExterna(dto.getReferenciaExterna());

            return mapToDTO(repo.save(pagos));
        });
    }

    public Optional<PagoResponseDTO> actualizarEstado(Long id, String estado){
        return repo.findById(id).map(pago ->{
            pago.setEstado(estado);

            return mapToDTO(repo.save(pago));
        });
    }

}
