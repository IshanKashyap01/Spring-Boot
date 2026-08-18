# Using Keycloak on Client Side

- We use this when the server is independent of the client and only interacts
through the API

```java
public class SecurityConfig
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(request -> request
            .requestMatchers("/login").permitAll()
            .anyRequest()
            .authenticated()
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
}
```

- `oauth2ResourceServer` verifies the JWT token received from the client

  - It is used for resource servers (REST/stateless)

- `JwtAuthenticationConverter` extracts the user roles inside the token
