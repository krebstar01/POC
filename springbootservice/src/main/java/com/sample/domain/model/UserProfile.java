package com.sample.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by justin on 01/03/2017.
 */
@Entity
@Table(name = "site_user_profile")
public class UserProfile implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "site_user_profile_id", nullable = false)
    private Integer id;

    @Column(name = "profile_type", length = 15, nullable = false)
    private UserProfileType profileType;

    @ManyToMany(mappedBy = "userProfiles", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public UserProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(UserProfileType profileType) {
        this.profileType = profileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (profileType != that.profileType) return false;
        return users != null ? users.equals(that.users) : that.users == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (profileType != null ? profileType.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }


// confused by many to many mapping?
// this stackoverflow posting explains it all, clearly!
//http://stackoverflow.com/questions/19280121/spring-and-or-hibernate-saving-many-to-many-relations-from-one-side-after-form

}
