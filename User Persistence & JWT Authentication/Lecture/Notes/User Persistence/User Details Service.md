# User Details Service

```java
@Service
public HotelUserDetailsService implements UserDetailsService
{
    @Autowired
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException
    {
        User user = repository.findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException(username + " invalid"));

        return new HotelUserDetails(user.getUsername(), user.getPassword());
    }
}
```

- The above service class should be in a `security` package that contains your
configurations and user details wrapper as well
