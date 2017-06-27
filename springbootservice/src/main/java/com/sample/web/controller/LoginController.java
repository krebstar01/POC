package com.sample.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by justin on 28/02/2017.
 */
@Controller
public class LoginController {

    // inject via application.properties
    //@Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping(value = "/login" , method = RequestMethod.GET  )
    public String login(Map<String, Object> model) {
        model.put("message", this.message);
        return "login";
    }


/*    @RequestMapping(value = "/loginError")
    public String loginError() {
        return "login";
    }*/


// http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/

// http://www.thymeleaf.org/doc/articles/springsecurity.html


// http://www.mkyong.com/spring-security/spring-security-form-login-using-database/

// http://www.journaldev.com/2736/spring-security-example-userdetailsservice

// http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/

// https://www.mkyong.com/spring-security/spring-security-hello-world-annotation-example/
}
