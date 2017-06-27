package com.sample.security;

import com.sample.data.repository.UserProfileRepository;
import com.sample.data.repository.UserRepository;
import com.sample.domain.model.User;
import com.sample.domain.model.UserProfile;
import com.sample.domain.model.UserProfileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by justin on 02/03/2017.
 */
@Component
public class UserCredentialsManager {


    public UserCredentialsManager() {
        super();
        pattern = Pattern.compile(EMAIL_PATTERN);
        passwordEncoder = new BCryptPasswordEncoder();
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserCredentialsManager userCredentialsManager;

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public String returnEncryptPassword(String password) {
        return passwordEncoder.encode(password);
    }


    public String retrieveUserSsoid() {
        return UUID.randomUUID().toString();
    }


    /**
     * Validate hex with regular expression
     *
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }


    @Transactional
    public User saveUserTransaction(String email, String firstName, String lastName, String password, UserProfileType[] profiles) {

        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSsoId(retrieveUserSsoid());
        user.setUserPassword(returnEncryptPassword(password));

        //Create profile list

        UserProfile profile = new UserProfile();
        profile.setProfileType(UserProfileType.USER);
        user.getUserProfiles().add(profile);
        userProfileRepository.save(profile);
        profile.getUsers().add(user);


        if (profiles != null && profiles.length > 0) {
            List<UserProfileType> profileList = Arrays.asList(profiles);

            for (UserProfileType profileValue : profileList) {
                profile = new UserProfile();
                profile.setProfileType(profileValue);
                userProfileRepository.save(profile);
                user.getUserProfiles().add(profile);
                profile.getUsers().add(user);
            }

        }

        return userRepository.save(user);

    }

}
