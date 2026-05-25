package com.proyecto.notificaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.notificaciones.model.Notificacion;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Long>{
    List<Notificacion> findByUsuarioId(Long usuarioId);

    //buscar notificaciones enviadas
    List<Notificacion> findByEnviado(boolean enviado);

    //buscar por el canal
    List<Notificacion> findByCanal(String canal);

    //buscar por su usuario y estado
    List<Notificacion> findByUsuarioIdAndEnviado(
        Long usuarioId,
        boolean enviado
    );
}
