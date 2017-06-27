package com.sample.data.repository;

import com.sample.domain.model.VirtualAlias;
import com.sample.domain.model.VirtualUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by justin on 15/02/2017.
 */
@Repository
public interface VirtualUserRepository extends JpaRepository<VirtualUser, Long> {

}
