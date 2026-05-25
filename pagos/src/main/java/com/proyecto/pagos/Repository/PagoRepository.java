package com.proyecto.pagos.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.pagos.Model.Pagos;

@Repository
public interface PagoRepository extends JpaRepository<Pagos, Long>{

    List<Pagos> findByOrdenId(Long ordenId);

    List<Pagos> findByEstado(String estado);

    List<Pagos> findByOrdenIdAndEstado(Long ordenId, String estado);

    List<Pagos> findByProveedor(String proveedor);

    boolean existsByOrdenId(Long ordenId);

    boolean existsByOrdenIdAndEstado(Long ordenId, String estado);
}