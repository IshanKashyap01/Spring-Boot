# Services in Spring MVC

```java
public interface UserService
{
    public boolean register(User user);

    public boolean signIn(User user);
}
```

```java
@Service
public class StudentService
{
    @Autowired
    StudentDataAccessObject studentRepository;

    @Override
    public boolean register(User user)
    {
        // check if user is valid
        // persist the user if everything is right
        studentRepository.save(user);
        return true;
    }
}
```
