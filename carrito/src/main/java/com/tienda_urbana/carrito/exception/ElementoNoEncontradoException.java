package com.tienda_urbana.carrito.exception;

public class ElementoNoEncontradoException extends RuntimeException {

    private final String elemento;
    private final Long id;

    public ElementoNoEncontradoException(String elemento, Long id){
        super(elemento + " con ID: " + id + " no existe");
        this.elemento = elemento;
        this.id = id;
    }

    public String getElemento(){
        return elemento;
    }
    
    public Long getId(){
        return id;
    }
}
