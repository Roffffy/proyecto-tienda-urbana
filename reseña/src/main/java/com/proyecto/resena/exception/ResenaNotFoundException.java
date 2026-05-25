package com.proyecto.resena.exception;

public class ResenaNotFoundException extends RuntimeException{

    private final Long ResenaId;

    public ResenaNotFoundException(Long id){
        super("Reseña no encontrada por su ID: " + id);
        this.ResenaId = id;
    }

    public Long getResenaId(){
        return ResenaId;
    }

}
