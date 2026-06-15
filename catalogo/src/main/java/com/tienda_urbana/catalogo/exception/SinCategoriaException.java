package com.tienda_urbana.catalogo.exception;

public class SinCategoriaException extends RuntimeException{


    public SinCategoriaException(){
        super("La categoria con ID: 1 NO DEBE ser eliminada");
    }

}
