package cz.upce.diplomovaprace;

import cz.upce.diplomovaprace.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // for security prepost atd anotace
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myAppUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/message/**").authenticated()
                .antMatchers("/challenge/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/message/list")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).clearAuthentication(true).permitAll();
        // SECURITY PRO H2 CONSOLI, ABY SME SE TAM DOSTALI JE NUTNY VYPNOUT CSRF PORTOZE v tom LOGIN INPUTU ZADNY csrf tokeny neposíláš žejo
        //https://stackoverflow.com/questions/41961270/h2-console-and-spring-security-permitall-not-working
        //https://www.logicbig.com/tutorials/spring-framework/spring-boot/jdbc-security-with-h2-console.html
        http.authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .cors().disable();
        // SECURITY PRO H2 END
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(myAppUserDetailsService).passwordEncoder(passwordEncoder);
        // auth
        //          .inMemoryAuthentication()
        //    .withUser(User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build());
    }
}