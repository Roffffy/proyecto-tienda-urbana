package com.proyecto.envio.exception;

public class EnvioNotFoundException extends RuntimeException{
    private final Long envioId;

    public EnvioNotFoundException(Long id){
        super("envio no encontrado por si id: " + id);
        this.envioId = id;
    }

    public Long getEnvioId(){
        return envioId;
    }

}
