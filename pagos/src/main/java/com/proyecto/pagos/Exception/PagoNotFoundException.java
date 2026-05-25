package com.proyecto.pagos.Exception;

public class PagoNotFoundException extends RuntimeException{
    private final Long pagoId;

    public PagoNotFoundException(Long id){
        super("pago no encontrado por su id" + id);
        this.pagoId = id;
    }

    public Long getPagoId(){
        return pagoId;
    }

}
