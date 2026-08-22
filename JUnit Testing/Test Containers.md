# Using Test Containers

```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>mysql</artifactId>
    <version>1.14.3</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>1.19.1</version>
    <scope>test</scope>
</dependency>
```

```java
/**
 * Make all repository tests inherit from this class so they all have access to
 * the same database container
 */
@DataJpaTest
@Testcontainers
// so the test container doesn't replace the actual database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class RepositoryTest
{
    protected static final MySQLContainer CONTAINER = new MySQLContainer("mysql:latest")
    .withDatabaseName("student-test-db")
    .withUsername("testUser")
    .withPassword("password");
    
    static
    {
        CONTAINER.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry)
    {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
    }
}
```
