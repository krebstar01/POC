package com.sample.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by justin on 01/03/2017.
 */
@Entity
@Table(name="persistent_login")
public class PersistentLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(name="series", unique=true, nullable=false)
    private String series;

    @Column(name="username", unique=true, nullable=false)
    private String username;

    @Column(name="user_token", unique=true, nullable=false)
    private String userToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_used", nullable=false)
    private Date lastUsed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }
}
