package com.sample.service.controller.handler;

import com.sample.configuration.GlobalProperties;
import com.sample.domain.model.exception.BadArgumentException;
import com.sample.domain.model.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.io.EOFException;
import java.io.IOException;

/**
 * Created by justin on 21/02/2017.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @Autowired
    GlobalProperties globalProperties;


    private boolean isDebug(){
        return globalProperties.isDebug();
    }
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * a dummy handler
     * if the configuration value "debug"  is set to true, extra information can be passed along to both the client error message as well as the log file
     */
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    void handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletResponse response) throws IOException {

        String errorMessage = "MethodArgumentTypeMismatchException YADDA Please try again and with a non empty string as 'name'";


        response.sendError(HttpStatus.BAD_REQUEST.value(), errorMessage);

        if (isDebug()) {
            logger.debug("MethodArgumentTypeMismatchException ###################################### YADDA YADDA YADDA");
        }
        logger.info(e.getMessage());
    }

    /**
     * handler for internal id not found exception
     * a com.sample.domain.model.exception.NotFoundException must be explicity thrown
     * if the configuration value "debug"  is set to true, extra information can be passed along to both the client error message as well as the log file
     */
    @ExceptionHandler(value = {NotFoundException.class})
    void handleNotFoundException(NotFoundException e, HttpServletResponse response) throws IOException {

        String errorMessage = "NotFoundException " + e.getMessage();

        if (isDebug()) {
            errorMessage += " Endpoint: " + e.getEndpoint();
            errorMessage += " HTTP Method: " + e.getHttpMethod();
        }

        response.sendError(HttpStatus.NOT_FOUND.value(), errorMessage);

        logger.info("NotFoundException ###################################### ");
        logger.info(errorMessage);
    }


    /**
     * handler for internal id not found exception
     * a com.sample.domain.model.exception.NotFoundException must be explicity thrown
     * if the configuration value "debug"  is set to true, extra information can be passed along to both the client error message as well as the log file
     */
    @ExceptionHandler(value = {BadArgumentException.class})
    void handleBadArgumentException(BadArgumentException e, HttpServletResponse response) throws IOException {

        String errorMessage = "BadArgumentException " + e.getMessage();

        if (isDebug()) {
            errorMessage += " Endpoint: " + e.getEndpoint();
            errorMessage += " HTTP Method: " + e.getHttpMethod();
        }

        response.sendError(HttpStatus.BAD_REQUEST.value(), errorMessage);

        logger.info("BadArgumentException ###################################### ");
        logger.info(errorMessage);
    }





    /**
     * catchall Exception handler for service
     * will collect any unexpected exceptions, log them and return a nice neat 500 the anyone consuming the service
     * if the configuration value "debug"  is set to true, extra information can be passed along to both the client error message as well as the log file
     */
    @ExceptionHandler(value = {Exception.class})
    void handlAllOtherExceptions(Exception e, HttpServletResponse response) throws IOException {

        String errorMessage = "Sorry, Something Unexpected happened";

        if (isDebug()) {
            errorMessage += " Exception cause: " + e.getCause();
            errorMessage += " Exception message: " + e.getMessage();
        }

        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);

        logger.info(errorMessage);
    }

    /**
     * catchall Error handler for service
     * will collect any unexpected exceptions, log them and return a nice neat 500 the anyone consuming the service
     * if the configuration value "debug"  is set to true, extra information can be passed along to both the client error message as well as the log file
     */
    @ExceptionHandler(value = {Error.class})
    void handleErrors(Error er, HttpServletResponse response) throws IOException {

        String errorMessage = "Sorry, Something Unexpected happened";

        if (isDebug()) {
            errorMessage += " An Error was thrown! something severe took place ";
            errorMessage += " Error cause: " + er.getCause();
            errorMessage += " Error cause: " + er.getCause();
            errorMessage += " Error message: " + er.getMessage();
        }

        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);

        logger.info(errorMessage);
    }


}



