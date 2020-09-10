package com.eman.securEman.configure;

import com.eman.securEman.model.UserPrincilpleDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

/**
 * Created by Mohammad on 8/7/2020.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{
    private UserPrincilpleDetailService userDetailService;

    public SecurityConfigure(UserPrincilpleDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
        auth.authenticationProvider(daoAuthenticationProvider());
                /*.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin"))
                .roles("ADMIN").authorities("ROLE_ADMIN","ACCESS_TEST1","ACCESS_TEST2")
                .and()
                .withUser("eman").password(passwordEncoder().encode("eman"))
                .roles("USER")
                .and()
                .withUser("manager").password(passwordEncoder().encode("manager"))
                .authorities("ACCESS_TEST1","ROLE_MANAGER");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/index").permitAll()
                .antMatchers("/profile/*").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN","MANAGER")
                .antMatchers("/public/api/test1").hasAuthority("ACCESS_TEST1")
                .antMatchers("/public/api/test2").hasAuthority("ACCESS_TEST2")
                .antMatchers("/public/api/users").hasAnyRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").permitAll()
                 .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login").and()
        .rememberMe()
        .tokenValiditySeconds(2592000)
        .key("mysecret!");
    }
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        return daoAuthenticationProvider;
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
