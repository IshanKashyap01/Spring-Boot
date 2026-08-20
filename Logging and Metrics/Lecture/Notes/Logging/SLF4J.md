# Logging Framework: SLF4J

- It comes included in `spring-boot-starter-web` so you can use them right-away

- It is a facade over different logging frameworks such as `LogBack` and
`Log4j`

```java
public class UserController
{
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public Connection getConnectionById(int id)
    {
        Connection connection = service.getConnectionById(id);
        if(connection == null)
        {
            logger.info("connection with id " + id + " not found");
        }
        return connection;
    }
}
```
