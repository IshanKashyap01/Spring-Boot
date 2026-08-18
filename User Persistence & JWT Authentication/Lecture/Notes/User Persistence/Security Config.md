# Security Configuration

```java
public class SecurityConfiguration
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
    {
        http.csrf().disable()
        .authorizeHttpRequests()
        .antMatchers("/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception
    {
        builder.getAuthenticationManager();
    }
}
```
