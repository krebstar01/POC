package com.sample.data.repository;

import com.sample.domain.model.User;
import com.sample.domain.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by justin on 15/02/2017.
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    

}
