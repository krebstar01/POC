package com.sample.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by justin on 08.06.17.
 */

@Entity
@Table(name = "virtual_domains")
public class VirtualDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;


    @NotEmpty
    @Column(name = "vd_name", nullable = false, unique = true)
    private String vdName;


/*    @JsonIgnore
    @ElementCollection(targetClass=VirtualAlias.class)
    @OneToMany(cascade = CascadeType.ALL)
    private Set<VirtualAlias> virtualAliases;


    @JsonIgnore
    @ElementCollection(targetClass=VirtualUser.class)
    @OneToMany(cascade = CascadeType.ALL)
    private Set<VirtualUser> virtualUsers;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVdName() {
        return vdName;
    }

    public void setVdName(String vdName) {
        this.vdName = vdName;
    }
}

