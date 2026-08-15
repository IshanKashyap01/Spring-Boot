# Custom User Details Service

```java
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService
{
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return repository.findByEmail(username)
        .orElseThrow(() -> new UserNotFoundException("user not found"));
    }
}
```
