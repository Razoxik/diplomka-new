package cz.upce.diplomovaprace.service;


import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.manager.SessionManager;
import cz.upce.diplomovaprace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

// todo: pri registraci ulozit username do DB s prvnim pismenem VELKYM a hash hesla celi MALYM!!
// todo: po zalozeni usera mu nasetovat rating na vsechny hry 1500
@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final String USER_ID_SESSION_ATTRIBUTE = "userId";
    // TY MOJE DAOCKA DAT JAKO REPOSITORY https://stackoverflow.com/questions/8550124/what-is-the-difference-between-dao-and-repository-patterns
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionManager sessionManager;

    /**
     * This method is overriden
     * Log user based on his credentials - username/password
     * !!!!!!!!!!! PASSWORD IS CASE SENSITIVE AND IT WILL ALWAYS BE! !!!!!!!!!!
     * Username is NOT case sensitive, we firstli put first letter to uppercase and rest to lower case
     * because we save it in this format to DB // AdMiN ---> Admin -> because we have it in this format in DB
     *
     * @param username
     * @return
     */
    //https://www.ekiras.com/2016/04/authenticate-user-with-custom-user-details-service-in-spring-security.html
    @Override
    public UserDetails loadUserByUsername(String username) {
        username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();
        User user = userRepository.findByUserName(username);
        String userName = user.getUserName();
        String password = user.getPassword();
        if (userName == null) {
            throw new UsernameNotFoundException(username);
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoleByRoleId().getRole());
        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(userName, password, Collections.singletonList(authority));

        sessionManager.setSessionAttribute(USER_ID_SESSION_ATTRIBUTE, userRepository.findByUserName(userName).getId());

        return userDetails;
    }

    // TOHLE BUDE POTREBA POUZE POKUD SE ROZHODNES MIT PRO UZIVATELE VICE ROLI NEZ JEDNU, ASI TO BUDE NAKONEC ROZUMNY NO
    // asi ROLE MxN mezi rolema pokud chces aby uzivatel mel vic roli, a CHCEME TOO?? ja nevim staci jedna ne?
    //   private Set<GrantedAuthority> getAuthorities(User user){
    //      Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
    //      for(Role role : user.getRoleByRoleId()) {
    //          GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
    //         authorities.add(grantedAuthority);
    //    }
    //   return authorities;
    //}
}