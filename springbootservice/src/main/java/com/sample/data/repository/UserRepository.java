package com.sample.data.repository;

import com.sample.domain.model.Customer;
import com.sample.domain.model.Order;
import com.sample.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by justin on 15/02/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findBySsoId(String ssoId);

    void deleteBySsoId(String ssoId);
}
