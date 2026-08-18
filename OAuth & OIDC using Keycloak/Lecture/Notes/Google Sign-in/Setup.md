# Setup for Google Sign-in

```yml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
```

- To test the user details after sign-in:

```java
@GetMapping("/userDetails")
public String getUserDetails(@AuthenticationPrincipal OidcUser user)
{
    return "username: " + user.getFullName() + ", email: " + user.getEmail();
}
```
