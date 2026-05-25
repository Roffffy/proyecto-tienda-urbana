package com.proyecto.devolucion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.devolucion.model.Devolucion;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Long>{
    
    List<Devolucion> findByUsuarioId(Long usuarioId);

    List<Devolucion> findByOrdenId(Long ordenId);

    List<Devolucion> findByEstado(String estado);

    List<Devolucion> findByUsuarioIdAndEstado(Long usuarioId, String estado);

    boolean existsByOrdenId(Long ordenId);
}
