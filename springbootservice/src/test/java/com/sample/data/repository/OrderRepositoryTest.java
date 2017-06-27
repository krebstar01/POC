package com.sample.data.repository;

import com.sample.Application;
import com.sample.data.repository.CustomerRepository;
import com.sample.data.repository.OrderRepository;
import com.sample.domain.model.Customer;
import com.sample.domain.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import util.TestDataGenerator;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by justin on 15/02/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = Application.class)
@ActiveProfiles(value = "test")
public class OrderRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;


    @Test
    public void testCreateOrderWithCustomer() {




        Customer customer = customerRepository.save(TestDataGenerator.createDummyCustomer());
        assertNotNull(customer);

        customer = customerRepository.findOne(customer.getCustomerId());
        assertNotNull(customer);


        Order order = orderRepository.save(TestDataGenerator.createDummyOrder(customer));
        assertNotNull(order);

        order = orderRepository.findOne(order.getOrderId());
        assertNotNull(order);
    }




}
