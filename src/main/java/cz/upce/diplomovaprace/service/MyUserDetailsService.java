package cz.upce.diplomovaprace.service;


import cz.upce.diplomovaprace.manager.SessionManager;
import cz.upce.diplomovaprace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MyUserDetailsService implements UserDetailsService {
    // TY MOJE DAOCKA DAT JAKO REPOSITORY https://stackoverflow.com/questions/8550124/what-is-the-difference-between-dao-and-repository-patterns
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionManager sessionManager;

    @Override
    public UserDetails loadUserByUsername(String username) { // name razox password razox
        String userName = userRepository.findUserByUsername(username).getUsername();
        String password = userRepository.findUserByUsername(username).getPassword();
        if (userName == null) {
            throw new UsernameNotFoundException(username);
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE");
        UserDetails userDetails = (UserDetails) new User(userName,
                password, Arrays.asList(authority));
        sessionManager.setSessionAttribute("userId", userRepository.findUserByUsername(userName).getUserId());

        return userDetails;
        //  return new MyUserPrincipal(user);
    }
}