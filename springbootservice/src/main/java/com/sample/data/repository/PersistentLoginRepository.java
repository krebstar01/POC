package com.sample.data.repository;

import com.sample.domain.model.PersistentLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import java.util.Date;
import java.util.List;

/**
 * Created by justin on 01/03/2017.
 */
public interface PersistentLoginRepository extends JpaRepository<PersistentLogin, Long> {

    PersistentLogin findBySeries(String series);
    void deleteByUsername(String username);

}





