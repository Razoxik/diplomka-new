package cz.upce.diplomovaprace.handler;

import cz.upce.diplomovaprace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

//http://www.jcombat.com/spring/custom-authentication-success-handler-with-spring-security
public class LoginHandlerCustom implements AuthenticationSuccessHandler {

    private static final String USER_ID_SESSION_ATTRIBUTE = "userId";

    @Autowired
    private UserRepository userRepository;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        HttpSession session = request.getSession();

        /*Set some session variables*/
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("uname", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());
        session.setAttribute(USER_ID_SESSION_ATTRIBUTE, userRepository.findByUserName(authUser.getUsername()).getId());

        /*Set target URL to redirect*/
        String targetUrl = determineTargetUrl(authentication);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (authorities.contains("ADMIN")) {
            return "/map";
            // return "/admin.htm";
        } else if (authorities.contains("OPERATOR")) {
            return "/map";
            // return "/user.htm";
        } else if (authorities.contains("USER")) {
            return "/map";
        } else {
            return "/map";
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