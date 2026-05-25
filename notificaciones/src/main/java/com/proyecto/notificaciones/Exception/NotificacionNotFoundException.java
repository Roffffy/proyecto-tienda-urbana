package com.proyecto.notificaciones.Exception;

public class NotificacionNotFoundException extends RuntimeException{

    private final Long notificacionId;

    public NotificacionNotFoundException(long id){
        super("Notificacion no encontrado por su Id: " + id);
        this.notificacionId = id;
    }

    public Long getNotificacionId(){
        return notificacionId;
    }

}
