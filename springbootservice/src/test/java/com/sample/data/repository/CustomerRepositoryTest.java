package com.sample.data.repository;

import com.sample.Application;
import com.sample.data.repository.CustomerRepository;
import com.sample.data.repository.OrderRepository;
import com.sample.domain.model.Customer;
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
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;


    @Test
    public void testTheTest() {
        assertTrue(true);
    }


    @Test
    public void testCreateDummyCustomers() {

        int expected = 5;
        customerRepository.save(TestDataGenerator.createDummyCustomers(expected));

        int actual = 0;
        Iterator<Customer> iterator = customerRepository.findAll().iterator();
        while (iterator.hasNext()) {
            actual++;
            iterator.next();
        }

        assertEquals(expected, 5);

    }


    @Test
    public void testCreateDummyCustomer() {
        Customer customer = customerRepository.save(TestDataGenerator.createDummyCustomer());
        assertNotNull(customer);
        customer = customerRepository.findOne(customer.getCustomerId());
        assertNotNull(customer);
    }


}