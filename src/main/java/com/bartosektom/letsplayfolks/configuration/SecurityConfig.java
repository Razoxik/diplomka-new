package com.bartosektom.letsplayfolks.configuration;

import com.bartosektom.letsplayfolks.handler.LoginHandlerCustom;
import com.bartosektom.letsplayfolks.service.impl.UserDetailsServiceCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // For security annotations (Preauthorize etc.)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceCustomImpl userDetailsServiceCustom;

    @Bean
    public LoginHandlerCustom myLoginHandlerCustom() {
        return new LoginHandlerCustom();
    }

    /**
     * Passwords are stored in encrypted form by BCrypt algorithm.
     *
     * @param auth {@link AuthenticationManagerBuilder}.
     * @throws Exception when password encoding fails.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsServiceCustom).passwordEncoder(passwordEncoder);
    }

    /**
     * For H2 console is needed to disable csrf tokens, cause login to H2 console does not have them, see links bellow.
     * https://stackoverflow.com/questions/41961270/h2-console-and-spring-security-permitall-not-working
     * https://www.logicbig.com/tutorials/spring-framework/spring-boot/jdbc-security-with-h2-console.html
     *
     * @param http {@link HttpSecurity}
     * @throws Exception when authorize request fail.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Access to pages
                .authorizeRequests()
                .antMatchers("/user/detail*").authenticated()
                .antMatchers("/friend/*").authenticated()
                .antMatchers("/message/*").authenticated()
                .antMatchers("/history/*").authenticated()
                .antMatchers("/challenge/*").authenticated()
                .antMatchers("/challenge/questionable/*").hasAnyAuthority("OPERATOR", "ADMIN")
                .antMatchers("/game/*").authenticated()
                .antMatchers("/game/approval/*").hasAuthority("ADMIN")
                .antMatchers("/h2-console").hasAuthority("ADMIN")
                .and()
                // Login
                .formLogin()
                .loginPage("/login").successHandler(myLoginHandlerCustom()).failureUrl("/login?error")
                .and()
                // Logout
                .logout()
                .logoutUrl("/logout").logoutSuccessUrl("/news?successMessage=logout").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).clearAuthentication(true).permitAll()
                .and()
                // H2 Console
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/*");
    }
}
