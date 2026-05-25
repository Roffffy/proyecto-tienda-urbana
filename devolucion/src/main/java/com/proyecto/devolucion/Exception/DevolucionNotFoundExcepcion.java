package com.proyecto.devolucion.Exception;

public class DevolucionNotFoundExcepcion extends RuntimeException{

    private final Long devolucionId;

    public DevolucionNotFoundExcepcion(Long id) {
        super("devolucion no encontrado por su id" + id);
        this.devolucionId = id;
    }

    public Long getDevolucionId(){
        return devolucionId;
    }
}
