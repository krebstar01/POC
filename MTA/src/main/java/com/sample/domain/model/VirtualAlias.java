package com.sample.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by justin on 08.06.17.
 */
@Entity
@Table(name = "virtual_aliases")
public class VirtualAlias {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "source", nullable = false)
    private String source;

    @NotEmpty
    @Column(name = "destination", nullable = false)
    private String destination;

    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="domain_id")
    private  VirtualDomain virtualDomain;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public VirtualDomain getVirtualDomain() {
        return virtualDomain;
    }

    public void setVirtualDomain(VirtualDomain virtualDomain) {
        this.virtualDomain = virtualDomain;
    }
}
