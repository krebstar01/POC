package com.sample.configuration;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by justin on 01/03/2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static String DEFAULT_CONTEXT = "app";


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //this pattern is needed to allow swagger documentation to be allowed before any other filters are applied
        String swagger_pattern[] = {
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/swagger-resources/configuration/ui",
                "/swagge‌​r-ui.html",
                "/rest/**"
        };


/* TODO add conditional configuration value (see GlobalProperties and application.yml) that sets value of variable
  "swagger_pattern[]"
*/

/*
        String swagger_pattern[] = {"/login"};
*/

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(swagger_pattern).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/loginError.html")
                .permitAll()
                .and()
                .logout()
                .permitAll();

/*
*csrf is disabled and usually should be unless you are using an ajax service AND need to prevent against cross-site attacks.
* http://www.baeldung.com/spring-security-csrf
*
*
*if csrf is enabled, please note it is global, AND you cannot disable it per controller, however as of springboot 4
* you can add a pattern like below
* csrf()
    .ignoringAntMatchers("/nocsrf","/ignore/startswith/**")
*
* */

    }


    /**
     * You can implement a simple security strategy where your user object overrides
     * the spring security class org.springframework.security.core.userdetails.UserDetails
     *
     * public class MyUser implements UserDetails
     *
     * where security is soley based on "front door security only" single role name only
     * http://www.torsten-horn.de/techdocs/Spring-Boot.html
     *
     *
     *
     * Or you can invoke a secure token (the style used in this example app)
     *
     * */


}
