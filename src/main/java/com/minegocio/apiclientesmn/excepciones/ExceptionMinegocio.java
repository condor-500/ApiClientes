package com.minegocio.apiclientesmn.excepciones;

public class ExceptionMinegocio  extends RuntimeException{

    private  String message ;

    public ExceptionMinegocio(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
