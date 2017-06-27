package com.sample.service.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sample.data.repository.CustomerRepository;
import com.sample.data.repository.OrderRepository;
import com.sample.domain.model.Customer;
import com.sample.domain.model.Order;
import com.sample.domain.model.exception.NotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by justin on 15/02/2017.
 */
@RestController
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderController extends BaseRestController{


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;


    @RequestMapping(value = "/orders=/{orderId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get order by id ", notes = "get existing order by Id")
    public Order getOrderById(@PathVariable("orderId") long orderId) {

        Order order = orderRepository.findOne(new Long(orderId));

        if(order==null){
            throw new NotFoundException("Could not find id " + orderId, "/orders=/{orderId}", "GET");
        }

        return order;
    }

    @RequestMapping(value = "/orders=/{orderId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "delete order by id ", notes = "delte existing order by Id")
    public void deleteOrder(@PathVariable("orderId") long orderId) {

        Order order = orderRepository.findOne(new Long(orderId));

        if(order==null){
            throw new NotFoundException("Could not find id " + orderId, "/orders=/{orderId}", "DELETE");
        }

        orderRepository.delete(order);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "get all order", notes = "display all existing orders")
    public List<Order> findAllOrders() {
        List<Order> results = new ArrayList<>();
        results.addAll(orderRepository.findAll());
        return results;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "create new order", notes = "create new order")
    public void saveOrder(@RequestParam(value = "customerId", required = true) long customerId, @RequestParam(value = "orderNumber", required = true) String orderNumber, @RequestParam(value = "orderActive", required = false, defaultValue = "true") boolean orderActive) {

        Customer customer = customerRepository.findOne(customerId);

        if(customer==null){
            throw new NotFoundException("Could not find id " + customerId, "/orders", "POST");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderActive(orderActive);
        order.setOrderNumber(orderNumber);
        order.setOrderDate(new Date());
        orderRepository.save(order);
    }


    @RequestMapping(value = "/orders=/{orderId}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "update existing order", notes = "update order name and/or orderActive boolean")
    public void updateOorder(@PathVariable("orderId") long orderId, @RequestParam(value = "orderNumber", required = true) String orderNumber, @RequestParam(value = "orderActive", required = false, defaultValue = "true") boolean orderActive) {
        Order order = orderRepository.findOne(new Long(orderId));

        if(order==null){
            throw new NotFoundException("Could not find id " + orderId, "/orders=/{orderId}", "PUT");
        }

        order.setOrderActive(orderActive);
        order.setOrderNumber(orderNumber);
        order.setOrderDate(new Date());
        orderRepository.save(order);
    }


}