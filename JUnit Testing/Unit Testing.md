# Unit Testing with JUnit

```java
@SpringBootTest
public class StudentServiceTest
{
    @MockitoBean
    StudentRepository repository;

    @InjectMocks
    StudentService service;

    @Test
    @DisplayName("Get Student by Id")
    @Order(1)
    public void shouldTestGetStudentById()
    {
        Student expected = new Student("Ishan", "XII", "C");
        when(repository.findById(1)).thenReturn(expected);
        Student result = service.getStudentById(1);
        Assertions.assertEquals(result, expected)
    }
}
```

- Before we start writing our tests, we mock all dependencies and inject them
into the test subject

- By default the tests are run in a random order, but if they share state, they
might require ordering
