package com.sample.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by justin on 15/02/2017.
 *
 * one customer has many orders
 */
@Entity
@Table(name = "customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="customer_id", unique = true, nullable = false)
    private long customerId;
    @Column(name = "customer_name")
    private String customerName;

    @JsonIgnore
    @ElementCollection(targetClass=Order.class)
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Order> orders;

    private boolean customerActive;

    public long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public boolean isCustomerActive() {
        return customerActive;
    }

    public void setCustomerActive(boolean customerActive) {
        this.customerActive = customerActive;
    }
}
