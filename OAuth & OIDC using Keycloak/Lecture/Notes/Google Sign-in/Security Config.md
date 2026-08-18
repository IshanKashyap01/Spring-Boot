# Security Configuration

```java
public class SecurityConfig
{
    private static final String ISSUER_URI = "accounts.google.com";
    private final UserService service;

    public GrantedAuthoritiesMapper userAuthoritiesMapper()
    {
        return (authorities) -> 
        {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            authorities.forEach(authority -> 
            {
                if(OidcUserAuthority.class.isInstance(authority))
                {
                    OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) authority;
                    Map<String, Object> userAttributesMap = oidcUserAuthority.getAttributes();
                    String issuer = oidcUserAuthority.getIdToken().getIssuer().toString();
                    if(issuer.contains(ISSUER_URI))
                    {
                        String email = userAttributesMap.get("email").toString();
                        // if user is null, the service throws an exception
                        User user = service.getUserByEmail(email);
                        mappedAuthorities.add(
                            new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())
                        );
                    }
                }
            });
            return mappedAuthorities;
        };
    }
}
```

- Google uses *Open ID Connect* for user authentication, which is why we're
using `OidcUserAuthority`
