package com.gszymans.simpleloanapp.services.serviceErrors;

public class LoanRejectedException extends RuntimeException {

    public LoanRejectedException(){};

    public LoanRejectedException(Throwable couse){
        super(couse);
    }

    public LoanRejectedException(String couse){
        super(couse);
    }
}
