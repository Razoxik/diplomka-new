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

/**
 * Login handler class.
 * For more info: http://www.jcombat.com/spring/custom-authentication-success-handler-with-spring-security
 */
public class LoginHandlerCustom implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        HttpSession session = request.getSession();

        // Set session variables.
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute(CommonConstants.USER_ID, userRepository.findByUserName(authUser.getUsername()).getId());

        // Set last login for user.
        com.bartosektom.letsplayfolks.entity.User user = userRepository.findByUserName(authUser.getUsername());
        user.setLastLogin(new Timestamp(new Date().getTime()));
        userRepository.save(user);

        // Set target URL to redirect.
        String targetUrl = determineTargetUrl(authentication);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Method prepared for future use - now redirect all user roles to the same URL.
     *
     * @param authentication {@link Authentication}
     * @return URL which is user redirected after login.
     */
    private String determineTargetUrl(Authentication authentication) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (authorities.contains("ADMIN")) {
            return "/news?successMessage=login";
        }
        if (authorities.contains("OPERATOR")) {
            return "/news?successMessage=login";
        }
        if (authorities.contains("USER")) {
            return "/news?successMessage=login";
        }
        throw new IllegalStateException();
    }
}
