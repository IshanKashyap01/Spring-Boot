# Adding Users

- By default, Spring creates a single random admin password at startup

- The below method overrides that behaviour by explicitly defining valid user
credentials

```java
@Bean
UserDetailsService users()
{
    UserDetails ud1 = User.builder()
    .username("name").password(passwordEncoder().encode("pass"))
    .roles("NORMAL")
    .build();
    UserDetails ud2 = User.builder()
    .username("user").password(passwordEncoder().encode("word"))
    .roles("NORMAL")
    .build();
    return new InMemoryUserDetailsManager(ud1, ud2);
}

@Bean
PasswordEncoder passwordEncoder()
{
    return new BCryptPasswordEncoder();
}
```

- The above code creates credential details of two users and stores the data
in the RAM

- Spring refuses to validate passwords as plain text, so you need an encoder
