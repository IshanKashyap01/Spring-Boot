# Spring Data JPA in Action

- Spring data JPA repositories provide transactional boundaries for their
built-in methods

- Following changes must be done in the `Application.yml` file before we use
Spring data JPA

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/<db-name>
    user: ${DB_USERNAME}$
    password: ${DB_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate.ddl-auto: update
```

```java
@Service
public class UserService
{
    @Autowired
    private final UserRepository repository;

    public User getUser(int id)
    {
        return repository.findById(id).get().orElseThrow(new UserNotFoundException(id));
    }

    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(user -> users.add(user));
    }

    public User saveUser(User user)
    {
        return repository.save(user);
    }

    public void delete(int id)
    {
        repository.deleteById(id);
    }

    public User update(User user)
    {
        return repository.save(user);
    }
}
```

- `findBId()` returns an `Optional<T>` whereas `findAll()` returns an
`Iterable<T>`

- `save()` returns the added/updated entity from the database

- `deleteById()` first checks if an entity with the given Id exists, then
deletes it
