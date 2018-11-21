package com.gszymans.simpleloanapp.services.serviceErrors;

public class NoSuchResourceException extends RuntimeException{

    public NoSuchResourceException(){};

    public NoSuchResourceException(Throwable couse){
        super(couse);
    }

    public NoSuchResourceException(String couse){
        super(couse);
    }

}