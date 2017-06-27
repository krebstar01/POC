package com.sample.domain.model;

import java.io.Serializable;


/**
 * Created by justin on 01/03/2017.
 */
public enum UserProfileType implements Serializable {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userProfileType;

    private UserProfileType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType() {
        return userProfileType;
    }

}
