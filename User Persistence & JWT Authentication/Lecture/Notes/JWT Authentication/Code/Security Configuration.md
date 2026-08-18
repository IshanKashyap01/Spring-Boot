# Security Configuration for JWT

```java
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig
{
    private final JwtAuthenticationFilter jutAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .antMatchers("/login", "/register").permitAll()
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
```
