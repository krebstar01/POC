package com.sample.service.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sample.data.repository.UserProfileRepository;
import com.sample.data.repository.UserRepository;
import com.sample.domain.model.User;
import com.sample.domain.model.UserProfileType;
import com.sample.domain.model.exception.BadArgumentException;
import com.sample.security.UserCredentialsManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin on 02/03/2017.
 */
@RestController
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserController extends BaseRestController{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserCredentialsManager userCredentialsManager;


    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create new system user", notes = "create new system user")
    public void saveUser(@RequestParam(value = "email", required = true) String email,
                         @RequestParam(value = "firstName", required = true) String firstName,
                         @RequestParam(value = "lastName", required = true) String lastName,
                         @RequestParam(value = "password", required = true) String password,
                         @RequestParam(value = "profiles[]") UserProfileType[] profiles) {


        if (!userCredentialsManager.validate(email)) {
            String message = "Your email address was invalid. Please enter a valid email address";
            throw new BadArgumentException(message, "/users", "POST");
        }

        User user = userCredentialsManager.saveUserTransaction(email, firstName, lastName, password, profiles);
        System.out.println(user);
        System.out.println("############################################################################################################################");
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all user", notes = "display all existing system users")
    public List<User> findAllUsers() {
        List<User> results = new ArrayList<>();
        results.addAll(userRepository.findAll());
        return results;
    }


}
