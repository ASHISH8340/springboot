package com.gl.EmployeeManagementSystem.config;

import com.gl.EmployeeManagementSystem.security.JwtAuthenticationEntryPoint;
import com.gl.EmployeeManagementSystem.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private  UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private JwtAuthenticationFilter authenticationFilter;




//    @Bean
//    public UserDetailsService userDetailsService(){
//
//       UserDetails normal= User.builder().username("Moon")
//               .password(passwordEncoder().encode("12345")).roles("NORMAL").build();
//
//        UserDetails admin= User.builder().username("Ashish")
//                .password(passwordEncoder().encode("56789")).roles("ADMIN").build();
//
//        //users create
//        //InMemoryUserDetailsManager- is implementation class of userDetailsService
//        return new InMemoryUserDetailsManager(normal,admin);
//
//    }

    //Basic Authentication
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        Database without jwt
//        http.csrf().disable().cors().disable().
//                authorizeRequests()
//                .anyRequest().authenticated()
//                .and().httpBasic();
//        return http.build();

//        with Jwt
        http.csrf().disable().cors().disable().
                authorizeRequests()
                //login api public
                .antMatchers("/auth/login").permitAll()
                .antMatchers(HttpMethod.POST,"/employee").permitAll()
                .antMatchers(HttpMethod.DELETE,"/employee/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }
    @Bean
    public  DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}

// UserDetails-represent userdetail
//UserDetailsService - service to get loadUserByUserName