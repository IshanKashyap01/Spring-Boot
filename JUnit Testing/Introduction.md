# Introduction to Testing

- *Unit testing* also known as *Black-box testing* tests only one, small unit
of code

- Such as a critical service layer function with complicated business logic or
control flow

- If the test subject requires external dependencies to perform, we *mock* them
using *Mockito*

- *Integration Testing* involves testing all the components and their wirings
i.e. no mocking is involved

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
</dependency>
```

- The above dependency include both `JUnit` and `Mockito`

- To unit-test with different configurations, create an `application-test.yml`

- For integration tests, create an `application-it.yml` file

- You can either use H2 instead of a database or test containers to use a
throw-away database instance
