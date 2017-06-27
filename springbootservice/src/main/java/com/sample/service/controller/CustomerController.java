package com.sample.service.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sample.data.repository.CustomerRepository;
import com.sample.domain.model.Customer;
import com.sample.domain.model.exception.NotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by justin on 15/02/2017.
 */
@RestController
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerController extends BaseRestController{


    @Autowired
    CustomerRepository customerRepository;




    @RequestMapping(value = "/customers=/{customerId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get customer by id ", notes = "get existing customer by Id")
    public Customer getCustomerById(@PathVariable("customerId") long customerId) {

        Customer customer = customerRepository.findOne(new Long(customerId));

        if(customer==null){
            throw new NotFoundException("Could not find id " + customerId, "/customers=/{customerId}", "GET");
        }

        return customer;

    }

    @RequestMapping(value = "/customers=/{customerId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "delete customer by id ", notes = "delte existing customer by Id")
    public void deleteCustomer(@PathVariable("customerId") long customerId) {

        Customer customer = customerRepository.findOne(new Long(customerId));

        if(customer==null){
            throw new NotFoundException("Could not find id " + customerId, "/customers=/{customerId}", "DELETE");
        }

        customerRepository.delete(customer);
    }


    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all customer", notes = "display all existing customers")
    public List<Customer> findAllCustomers() {
        List<Customer> results = new ArrayList<>();
        results.addAll(customerRepository.findAll());
        return results;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create new customer", notes = "create new customer")
    public void saveCustomer(@RequestParam(value = "customerName", required = true) String customerName, @RequestParam(value = "customerActive", required = false, defaultValue = "true") boolean customerActive) {
        Customer customer = new Customer();
        customer.setCustomerActive(customerActive);
        customer.setCustomerName(customerName);
        customerRepository.save(customer);
    }


    @RequestMapping(value = "/customers=/{customerId}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "update existing customer", notes = "update customer name and/or customerActive boolean")
    public void updateCustomer(@PathVariable("customerId") long customerId, @RequestParam(value = "customerName", required = true) String customerName, @RequestParam(value = "customerActive", required = false, defaultValue = "true") boolean customerActive) {

        Customer customer = customerRepository.findOne(new Long(customerId));

        if(customer==null){
            throw new NotFoundException("Could not find id " + customerId, "/customers=/{customerId}", "PUT");
        }


        customer.setCustomerActive(customerActive);
        customer.setCustomerName(customerName);
        customerRepository.save(customer);
    }







}