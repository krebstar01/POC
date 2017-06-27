package com.sample.domain.model.exception;

/**
 * Created by justin on 23/02/2017.
 */
public class BasesampleServiceException extends RuntimeException {


    String endpoint;
    String httpMethod;


    public BasesampleServiceException(String message){
        super(message);
    }

    public BasesampleServiceException(String message, String endpoint){
        super(message);
        this.endpoint = endpoint;
    }

    public BasesampleServiceException(String message, String endpoint, String httpMethod){
        super(message);
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
    }


    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
