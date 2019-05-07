package com.bartosektom.letsplayfolks.service.impl;


import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceCustomImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * This method is overridden from UserDetailsService and logs in user, based on his credentials - username/password.
     * For more info: https://www.ekiras.com/2016/04/authenticate-user-with-custom-user-details-service-in-spring-security.html
     * !!! PASSWORD IS CASE SENSITIVE !!!
     * Username is NOT case sensitive.
     * We firstly put first letter to uppercase and then rest to lower case. (user names are stored inn this format in DB).
     * eg. peTEr -> Peter
     *
     * @param username User name
     * @return {@link UserDetails}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        // First letter in uppercase, rest in lower. User names are stored in this format.
        User user = userRepository.findByUserName(username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase());
        String userName = user.getUserName();
        String password = user.getPassword();
        if (userName == null) {
            throw new UsernameNotFoundException(username);
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoleByRoleId().getRole());

        return new org.springframework.security.core.userdetails.User(userName, password, Collections.singletonList(authority));
    }
}
