package com.sample.domain.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by justin on 15/02/2017.
 *  many Orders to one Customer
 */
@Entity
@Table(name = "customer_order")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", unique = true, nullable = false)
    private long orderId;
    @Column(name = "order_number")
    private String orderNumber;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "is_order_active")
    private boolean isOrderActive;

    @ManyToOne(cascade=CascadeType.REMOVE)
    private Customer customer;


    public long getOrderId() {
        return orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isOrderActive() {
        return isOrderActive;
    }

    public void setOrderActive(boolean orderActive) {
        isOrderActive = orderActive;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
