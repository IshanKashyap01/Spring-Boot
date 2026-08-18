# Using Keycloak on Server Side

- We use this when our server also serves webpages to the client

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
        .oauth2Login(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/home")
        );
        return http.build();
    }

    public GrantedAuthoritiesMapper userAuthoritiesMapper()
    {
        return (authorities) -> 
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
```

- `GrantedAuthoritiesMapper` operates on an `OAuth2UserAuthority` object
fetched from Keycloak

  - It is used exclusively during OAuth2 login (Stateful/SSR) process

- First it get `realm_access` of the token, which contains the user's roles

- It extracts the roles found into a `Set` of `GrantedAuthority`
