package com.sample.service.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UnknownFormatConversionException;


/*
*
 * Created by justin on 15/02/2017.
*/
@RestController
public class HelloController extends BaseRestController{


    @ApiOperation(value = "Gets a hello resource. Version 1 - (version in URL)", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hello resource found"),
            @ApiResponse(code = 404, message = "Hello resource not found")
    }
    )
@RequestMapping(value = "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Greetings from Spring Boot!";
    }


    @ApiOperation(value = "This resource throws an Unknown Exception which is dealt with by the GlobalExceptionHandler! Version 1 - (version in URL)", response = String.class)
    @RequestMapping(value = "/unknownException", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void throwUnknownException() {
        throw new UnknownFormatConversionException("No idea what happened here");
    }

    @ApiOperation(value = "This resource throws an Unknown Error which is dealt with by the GlobalExceptionHandler! Version 1 - (version in URL)", response = String.class)
    @RequestMapping(value = "/unknownError", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void throwUnknownError() {
        throw new Error("Something really icky happened");
    }




}