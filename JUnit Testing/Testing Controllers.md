# Unit Testing Controllers

```java
@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest
{
    @MockitoBean
    private StudentService service;

    @Test
    public void shouldGetStudentById(MockMvc mvc) throws Exception
    {
        Student expected = new Student("Ishan", "XII", "C");
        when(service.getStudentById(1)).thenReturn(expected);
        mvc.perform(MockMvcRequestBuilders.get("/students/1"))
        .andExpect(MockMvcResultMatchers.status.isOk());
    }
}
```
