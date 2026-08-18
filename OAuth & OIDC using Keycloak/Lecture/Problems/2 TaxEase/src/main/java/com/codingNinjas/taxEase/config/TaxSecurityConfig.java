package com.codingNinjas.taxEase.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class TaxSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/user/signup","/login")
            .permitAll().anyRequest().authenticated()
        )
        .oauth2Login(oauth2 -> oauth2
            .loginPage("/login")
            .defaultSuccessUrl("/user/all")
        )
        .oauth2ResourceServer(server -> server
            .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
        );
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter()
    {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> 
        {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            Map<String, Object> claims = jwt.getClaims();
            Object realmAccess = claims.get("realm_access");
            if(realmAccess != null)
            {
                LinkedTreeMap<String, List<String>> roleMap = (LinkedTreeMap<String, List<String>>) realmAccess;
                List<String> roles = new ArrayList<>(roleMap.get("roles"));
                authorities.addAll(roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList())
                );
            }
            return authorities;
        });
        return converter;
    }

    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            authorities.forEach(authority -> {
                if(OAuth2UserAuthority.class.isInstance(authority)) {

                    OAuth2UserAuthority oAuth2UserAuthority = (OAuth2UserAuthority) authority;
                    Map<String, Object> userAttributesMap = oAuth2UserAuthority.getAttributes();


                    if(userAttributesMap.containsKey("realm_access")) {
                        Map<String, Object> realmAccessMap = (Map<String, Object>) userAttributesMap.get("realm_access");
                        if(realmAccessMap.containsKey("roles")) {
                            List<String> roles = (List<String>)realmAccessMap.get("roles");
                            mappedAuthorities.addAll(roles.stream()
                                    .map(role -> new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()))
                                    .collect(Collectors.toSet()));
                        }
                    }
                }
            });
            return mappedAuthorities;
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("https://lemur-15.cloud-iam.com/auth/realms/ishan-kashyap");
    }
}

