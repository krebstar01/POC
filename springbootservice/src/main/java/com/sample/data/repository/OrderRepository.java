package com.sample.data.repository;

import com.sample.domain.model.Customer;
import com.sample.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by justin on 15/02/2017.
 */
@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {

    @Query("select o.orderNumber from Order o, Customer c where o.customer.id = c.id and c.customerName = :customerName")
    public List<Customer> findOrderByCustomerName(String customerName);

}
