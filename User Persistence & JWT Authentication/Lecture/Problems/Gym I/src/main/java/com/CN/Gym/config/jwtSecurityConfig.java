package com.CN.Gym.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.CN.Gym.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

/*
1. Add relevant annotations to make this class a configuration class and enable method leve security.
2. Create authenticationManager, passwordEncoder bean.
3. Create securityFilterChain bean with httpBasic security and use antMatcher to make "/user/register"
an open API.
*/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class jwtSecurityConfig
{
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .antMatchers("/user/register", "/login", "/auth/login").permitAll()
            .antMatchers("/gym/**").hasRole("ADMIN")
            .anyRequest()
            .authenticated()
        )
        .sessionManagement
        (
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }
}