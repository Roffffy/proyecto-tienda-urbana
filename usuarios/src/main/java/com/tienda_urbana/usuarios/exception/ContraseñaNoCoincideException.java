package com.tienda_urbana.usuarios.exception;

public class ContraseñaNoCoincideException extends RuntimeException{

    public ContraseñaNoCoincideException(){
        super("La contraseña antigua no coincide");
    }
}
