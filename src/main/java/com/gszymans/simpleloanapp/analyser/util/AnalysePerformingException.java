package com.gszymans.simpleloanapp.analyser.util;

public class AnalysePerformingException extends RuntimeException {

    public AnalysePerformingException(){};

    public AnalysePerformingException(Throwable couse){
        super(couse);
    }
}
