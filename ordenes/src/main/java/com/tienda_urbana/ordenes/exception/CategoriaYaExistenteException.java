package com.tienda_urbana.ordenes.exception;

public class CategoriaYaExistenteException extends RuntimeException{

    private final String nombreCategoria;
    
    public CategoriaYaExistenteException(String nombreCategoria){
        super("La categoria '"+nombreCategoria+"' ya existe");
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreCategoria(){
        return nombreCategoria;
    }

}
