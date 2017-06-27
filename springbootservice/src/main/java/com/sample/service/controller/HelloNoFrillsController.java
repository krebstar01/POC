package com.sample.service.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UnknownFormatConversionException;


/*
*
 * Created by justin on 15/02/2017.
*/
@RestController
public class HelloNoFrillsController extends BaseRestController {


@RequestMapping(value = "/helloNoFrills", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String helloNoFrills() {
        return "Greetings from Spring Boot! Nothing Much going on here";
    }
}