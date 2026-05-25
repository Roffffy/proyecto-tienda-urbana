package com.proyecto.resena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.resena.model.Resena;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long>{

    List<Resena> findByProductoId(Long productoId);

    List<Resena> findByUsuarioId(Long usuarioId);
}
