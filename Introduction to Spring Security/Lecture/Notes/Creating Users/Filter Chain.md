# Creating Security Filter Chain

The code below:

1. Disables CSRF protection,

2. Blocks all public access and,

3. Enables a standard, auto-generated login page to input user credentials

```java
@Configuration
@EnableWebSecurity
public class HotelSecurityConfiguration
{
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
        .authorizeHttpRequests()
        .anyRequest().authenticated()
        .and().formLogin();

        return http.build();
    }
}
```

## Annotations

- `@Configuration` indicates that the object is a source of bean definitions

- `@EnableWebSecurity` tells spring to use your configuration and turn off its
default setup

- `@Bean` method-level annotation used within a `@Configuration` class

  - Tells spring container to manage the returned object as a *Spring Bean*

## Functions

- `formLogin()` tells Spring to use form-based login mechanism

- `build()` processes all individual configurations and compiles them into a
functioning sequence of servlet filters
