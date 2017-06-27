package com.sample.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by justin on 01/03/2017.
 */
@Entity
@Table(name = "virtual_users")
public class VirtualUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;



    @NotEmpty
    @Column(name = "vu_password", nullable = false)
    private String vuPassword;

    @NotEmpty
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="domain_id")
    private  VirtualDomain virtualDomain;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVuPassword() {
        return vuPassword;
    }

    public void setVuPassword(String vuPassword) {
        this.vuPassword = vuPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VirtualDomain getVirtualDomain() {
        return virtualDomain;
    }

    public void setVirtualDomain(VirtualDomain virtualDomain) {
        this.virtualDomain = virtualDomain;
    }
}
