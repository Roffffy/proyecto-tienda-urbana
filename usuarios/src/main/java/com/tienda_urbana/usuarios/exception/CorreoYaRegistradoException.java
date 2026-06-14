package com.tienda_urbana.usuarios.exception;

public class CorreoYaRegistradoException extends RuntimeException{

    private final String email;

    public CorreoYaRegistradoException(String email){
        super("El correo '"+email+"' ya se encuentra registrado");
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

}
