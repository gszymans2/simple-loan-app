package com.gszymans.simpleloanapp.services.serviceErrors;

public class BadInputParamsException extends RuntimeException {

    public BadInputParamsException(){};

    public BadInputParamsException(Throwable couse){
        super(couse);
    }

    public BadInputParamsException(String couse){
        super(couse);
    }

}
