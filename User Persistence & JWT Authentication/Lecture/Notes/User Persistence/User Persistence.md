# User Persistence

To store user credentials to the database, you'll need the following:

1. A controller to handles login/register requests

2. A repository to persist the user entity to the database

3. A service that sits in the middle

4. A user entity class with a many to many relationship with roles

5. A role entity class that represents the various roles a user can have

6. A Data Transfer Object for user credentials

7. A class that implements `UserDetails` and contains the user entity class

8. A user details service to facilitate login/registration

- The controller, repository, service, DTO and entity classes won't be
any different

- Spring encodes the login password it receives from the user and matches
it against the database entry

```java
@Autowired
private final PasswordEncoder encoder;

public void registerUser(UserDto dto)
{
    User user = new User();
    user.setUsername(dto.getUsername());
    user.setPassword(encoder.encode(dto.getPassword()));
    user.getRoles().add("NORMAL");
    repository.save(user);
}
```

- B crypt cannot be decoded, so you must encode passwords before storing
them
