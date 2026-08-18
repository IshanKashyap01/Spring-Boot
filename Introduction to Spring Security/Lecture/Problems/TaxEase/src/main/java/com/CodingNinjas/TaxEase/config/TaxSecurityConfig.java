package com.CodingNinjas.TaxEase.config;

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
public class TaxSecurityConfig
{
    /*
        This is the security configuration class for the application, complete the class by doing the following:
        a. Use appropriate annotations.
        b. Create a securityFilterChain bean
        c. Create a passwordEncoder bean.
        d. Create a userDetailService bean in which create a user with normal role.
     */
    @Bean
    SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
        .authorizeHttpRequests()
        .anyRequest().authenticated()
        .and().httpBasic();

        return http.build();
    }

    @Bean
    UserDetailsService buildUserDetails()
    {
        UserDetails normal = User.builder()
        .username("john").password(encoder().encode("john123"))
        .roles("NORMAL")
        .build();

        UserDetails admin = User.builder()
        .username("steve").password(encoder().encode("abc123"))
        .roles("ADMIN")
        .build();

        return new InMemoryUserDetailsManager(normal, admin);
    }

    @Bean
    PasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }
}