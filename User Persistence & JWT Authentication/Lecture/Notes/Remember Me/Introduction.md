# Remember Me

- To use it, first we need to use form-based authentication

```java
{
    http.csrf().disable()
    .authorizeHttpRequests()
    .antMatchers("/user/register").permitAll()
    .and()
    .rememberMe().userDetailsService(userDetailsService)
    .and()
    .formLogin().loginPage("/login").permitAll()
    // optional
    .and()
    .logout().deleteCookies("remember-me");

    return http.build();
}
```

- We will be using a custom login page that is sent using the controller below

```java
@Controller
public class LoginController
{
    @GetMapping("/login")
    public String getLoginPage()
    {
        return "login";
    }
}
```
