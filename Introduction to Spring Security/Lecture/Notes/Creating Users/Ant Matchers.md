# Ant Matchers

```java
http.authorizeHttpRequests()
.antMatchers("/admin/**").hasRole("ADMIN")
.anyRequest().authenticated()
.and().httpBasic();
```

- `httpBasic()` tells Spring to use HTTP basic authentication

- `antMatchers()` targets specific sections of your API for stricter
control

- `anyRequest()` catches every other URL that isn't explicitly dealt with
