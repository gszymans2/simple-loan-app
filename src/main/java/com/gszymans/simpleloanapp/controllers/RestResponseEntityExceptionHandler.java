package com.gszymans.simpleloanapp.controllers;

import com.gszymans.simpleloanapp.analyser.util.AnalysePerformingException;
import com.gszymans.simpleloanapp.services.serviceErrors.BadInputParamsException;
import com.gszymans.simpleloanapp.services.serviceErrors.LoanRejectedException;
import com.gszymans.simpleloanapp.services.serviceErrors.NoSuchResourceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({LoanRejectedException.class})
    public ResponseEntity<Object> loanRejectedException(Exception exception, WebRequest request){

        return new ResponseEntity<Object>("Application Rejected", new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({AnalysePerformingException.class})
    public ResponseEntity<Object> analysePerformingException(Exception exception, WebRequest request){

        return new ResponseEntity<Object>("Exception during performing analyses", new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({BadInputParamsException.class})
    public ResponseEntity<Object> badInputParamsException(Exception exception, WebRequest request){

        return new ResponseEntity<Object>("Bad input params", new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({NoSuchResourceException.class})
    public ResponseEntity<Object> noSuchResourceException(Exception exception, WebRequest request){

        return new ResponseEntity<Object>("No such resource", new HttpHeaders(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> unspecifiedException(Exception exception, WebRequest request){

        return new ResponseEntity<Object>("Unspecified Exception", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}