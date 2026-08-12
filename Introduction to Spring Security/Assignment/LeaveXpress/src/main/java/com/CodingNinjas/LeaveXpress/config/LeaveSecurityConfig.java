package com.CodingNinjas.LeaveXpress.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LeaveSecurityConfig
{
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
        .authorizeHttpRequests()
        .anyRequest().authenticated()
        .and().httpBasic();

        return http.build();
    }

    @Bean
    UserDetailsService users()
    {
        UserDetails employee = User.builder()
        .username("employee")
        .password(passwordEncoder().encode("employee123"))
        .roles("EMPLOYEE")
        .build();

        UserDetails manager = User.builder()
        .username("manager")
        .password(passwordEncoder().encode("manager123"))
        .roles("MANAGER")
        .build();

        return new InMemoryUserDetailsManager(employee, manager);
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}