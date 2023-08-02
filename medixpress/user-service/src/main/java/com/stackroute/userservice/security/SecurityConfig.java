package com.stackroute.userservice.security;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    public void configure(HttpSecurity http) throws Exception {

//        http.cors();//cross-origin-resource-sharing
        http.csrf().disable();

        http.antMatcher("/**").authorizeRequests()
                .antMatchers("/api/v1/user/**").permitAll()
//                .antMatchers("/user-service/api/v1/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();



    }

}