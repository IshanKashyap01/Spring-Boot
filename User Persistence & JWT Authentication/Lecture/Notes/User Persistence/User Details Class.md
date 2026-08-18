# User Details Class

```java
public HotelUserDetails implements UserDetails
{
    @Autowired
    private User user;

    @Override
    public String getUsername()
    {
        return user.getUsername();
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return user.getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
        .collect(Collectors.toList())
    }
}
```

- Other methods required to be overridden should return true as we don't need
them right now
