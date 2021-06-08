package com.example.smartparking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().mvcMatchers(HttpMethod.POST, "/smartparking/spot").hasAuthority("ADMIN")
                                .mvcMatchers(HttpMethod.PUT, "/smartparking/spot").hasAuthority("ADMIN")
                                .mvcMatchers(HttpMethod.DELETE, "/smartparking/spot").hasAuthority("ADMIN")
                                .mvcMatchers(HttpMethod.GET, "/smartparking/spot").authenticated()
                                .mvcMatchers(HttpMethod.POST, "/smartparking/user").permitAll()
                                .mvcMatchers(HttpMethod.GET, "/smartparking/user").authenticated()
                                .mvcMatchers(HttpMethod.DELETE, "/smartparking/user").authenticated()
                                .mvcMatchers(HttpMethod.PATCH, "/smartparking/user").authenticated()
                                .mvcMatchers(HttpMethod.POST, "/smartparking/reservation").hasAuthority("CLIENT")
                                .mvcMatchers(HttpMethod.GET, "/smartparking/reservation").hasAuthority("CLIENT")
                                .mvcMatchers(HttpMethod.PATCH, "/smartparking/reservation").hasAuthority("CLIENT")
                                .mvcMatchers(HttpMethod.DELETE, "/smartparking/reservation").hasAuthority("CLIENT")
                                .mvcMatchers("/park").permitAll()
                                .anyRequest().permitAll();

    }
}
