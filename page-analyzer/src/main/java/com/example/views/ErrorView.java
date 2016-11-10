package com.example.views;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.ws.rs.WebApplicationException;

import io.dropwizard.views.View;

public class ErrorView extends View{
    //private final Exception exception;
    //private final String stackTrace;
    private final int faultNumber;
    private final Class exceptionType;
    private final String exceptionMessage;
    private final Exception exception;
    private String stackTrace;


    public ErrorView(Exception e, String template) {
        super(template, Charset.forName("UTF-8"));
        this.exceptionType = e.getClass();
        this.exceptionMessage = e.getMessage();


        if(e instanceof WebApplicationException){
            this.faultNumber = ((WebApplicationException) e).getResponse().getStatus();
        } else {
            this.faultNumber = 500;
        }

        this.exception = e;

        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        exception.printStackTrace(pw);

       this.stackTrace = sw.getBuffer().toString();
    }


    public Exception getException() {
        return exception;
    }

    public String getStackTrace() {
        return stackTrace;
    }


    public Class getExceptionType() {
        return exceptionType;
    }

    public int getFaultNumber (){
        return faultNumber;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
