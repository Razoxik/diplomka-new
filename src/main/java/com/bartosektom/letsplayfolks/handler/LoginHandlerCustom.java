package com.bartosektom.letsplayfolks.handler;

import com.bartosektom.letsplayfolks.constants.CommonConstants;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

//http://www.jcombat.com/spring/custom-authentication-success-handler-with-spring-security
public class LoginHandlerCustom implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        HttpSession session = request.getSession();

        /*Set some session variables*/
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("uname", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());
        session.setAttribute(CommonConstants.USER_ID, userRepository.findByUserName(authUser.getUsername()).getId());

        com.bartosektom.letsplayfolks.entity.User user = userRepository.findByUserName(authUser.getUsername());
        user.setLastLogin(new Timestamp(new Date().getTime()));
        userRepository.save(user);

        /*Set target URL to redirect*/
        String targetUrl = determineTargetUrl(authentication);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (authorities.contains("ADMIN")) {
            return "/news?fromLogin=true";
            // return "/admin.htm";
        } else if (authorities.contains("OPERATOR")) {
            return "/news?fromLogin=true";
            // return "/user.htm";
        } else if (authorities.contains("USER")) {
            return "/news?fromLogin=true";
        } else {
            return "/news?fromLogin=true";
//            throw new IllegalStateException();
        }
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}