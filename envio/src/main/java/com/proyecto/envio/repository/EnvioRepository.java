package com.proyecto.envio.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.envio.model.Envio;

import feign.Param;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long>{
    List<Envio> findByOrdenId(Long ordenId);

    List<Envio> findByEstado (String estado);

    List<Envio> findByDireccion (String direccion);

    @Query("SELECT e FROM Envio e WHERE e.estado = :estado ORDER BY e.despachadoEn DESC")
    List<Envio> buscarPorEstado(@Param("estado") String estado);

    @Query("SELECT e FROM Envio e WHERE e.entregadoEn >= :fecha")
    List<Envio> buscarEntregadosDesde(@Param("fecha") LocalDateTime fecha);
}
