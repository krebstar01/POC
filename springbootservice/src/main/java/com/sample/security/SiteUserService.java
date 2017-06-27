package com.sample.security;

import com.sample.data.repository.UserRepository;
import com.sample.domain.model.User;
import com.sample.domain.model.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by justin on 01/03/2017.
 */
public class SiteUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SiteUserService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        User user = userRepository.findBySsoId(ssoId);

        if(user==null){
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getUserPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(UserProfile userProfile : user.getUserProfiles()){
            logger.info("UserProfile : {}", userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getProfileType()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }

}
