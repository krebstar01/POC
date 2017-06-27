package com.sample.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by justin on 01/03/2017.
 */
@Controller
public class MainMenuController {

    // inject via application.properties
    //@Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping(value = "/mainMenu" , method = RequestMethod.GET  )
    public String mainMenu() {
        return "mainMenu";
    }

}
