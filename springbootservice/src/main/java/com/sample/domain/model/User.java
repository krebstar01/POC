package com.sample.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by justin on 01/03/2017.
 */
@Entity
@Table(name = "site_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "site_user_id", nullable = false)
    private Long id;

    @NotEmpty
    @Column(name = "sso_id", unique = true, nullable = false)
    private String ssoId;

    @NotEmpty
    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @NotEmpty
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotEmpty
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserProfileJoin",
            joinColumns = {@JoinColumn(name = "site_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "site_user_profile_id")})
    private Set<UserProfile> userProfiles = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }


// confused by many to many mapping?
// this stackoverflow posting explains it all, clearly!
//http://stackoverflow.com/questions/19280121/spring-and-or-hibernate-saving-many-to-many-relations-from-one-side-after-form


}
