# Authentication Controller and Service

## Authentication Controller

```java
@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController
{
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
    {
        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
    }
}
```

## Authentication Service

```java
@Service
@RequiredArgsConstructor
public class AuthService
{
    private final AuthenticationManager manager;
    private final JwtAuthenticationHelper helper;

    public JwtResponse login(JwtRequest request)
    {
        authenticate(request.getUsername(), request.getPassword());
        String token = this.helper.generateToken(request.getUsername());
        return JwtResponse.builder().jwtToken(token).build();
    }

    private void authenticate(String username, String password)
    {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        try
        {
            this.manager.authenticate(authToken);
        }
        catch(BadCredentialsException e)
        {
            throw new BadCredentialsException("invalid username or password");
        }
    }
}
```
