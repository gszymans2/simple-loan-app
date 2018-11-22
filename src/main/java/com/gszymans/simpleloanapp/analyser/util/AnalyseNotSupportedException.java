package com.gszymans.simpleloanapp.analyser.util;

public class AnalyseNotSupportedException extends RuntimeException {

    public AnalyseNotSupportedException(){};

    public AnalyseNotSupportedException(Throwable couse){
        super(couse);
    }

    public AnalyseNotSupportedException(String couse){
        super(couse);
    }
}
