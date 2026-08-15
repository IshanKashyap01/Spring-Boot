# JWT Request and Response

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest
{
    private String username;
    private String password;
}
```

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse
{
    private String jwtToken;
}
```
