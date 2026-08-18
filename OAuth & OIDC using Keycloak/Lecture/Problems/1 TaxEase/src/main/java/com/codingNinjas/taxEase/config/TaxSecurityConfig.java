package com.codingNinjas.taxEase.config;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.jwt.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class TaxSecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(request -> request
            .requestMatchers("/login", "/user/signup").permitAll()
            .anyRequest()
            .authenticated()
        )
        .oauth2Login(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/user/all")
        );
        return http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper()
    {
        return authorities -> 
        {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            authorities.forEach(authority -> 
            {
                if(OAuth2UserAuthority.class.isInstance(authority))
                {
                    OAuth2UserAuthority oAuth2UserAuthority = (OAuth2UserAuthority) authority;
                    Map<String, Object> userAttributesMap = oAuth2UserAuthority.getAttributes();
                    if(userAttributesMap.containsKey("realm_access")) 
                    {
                        Map<String, Object> realmAccessMap = (Map<String, Object>) userAttributesMap.get("realm_access");
                        if(realmAccessMap.containsKey("roles"))
                        {
                            List<String> roles = (List<String>)realmAccessMap.get("roles");
                            mappedAuthorities.addAll
                            (
                                roles.stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()))
                                .collect(Collectors.toSet())
                            );
                        }
                    }
                }
            });
            return mappedAuthorities;
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() 
    {
        return JwtDecoders.fromIssuerLocation("https://lemur-15.cloud-iam.com/auth/realms/ishan-kashyap");
    }
}