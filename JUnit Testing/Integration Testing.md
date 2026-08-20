# Integration Testing

```java
@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceIT extends RepositoryTest
{
    @Test
    @DisplayName("get student by Id")
    @WithMockUser(roles = "ADMIN")
    public void shouldGetStudentById(MockMvc mvc) throws Exception
    {
        mvc.perform(get("/students")).andExpect(status.isOk());
    }

    @Test
    @DisplayName("create student")
    @WithMockUser(roles = "ADMIN")
    public void shouldTestCreateStudent(MockMvc mvc) throws Exception
    {
        Student student = new Student("Ishan", "Kashyap", "XII");
        mvc.perform(
            MockMvcRequestBuilders
            .post("/students")
            .contentType("application/json")
            .content(toJson(student))
        )
        .andExpect(status().isOk());
    }
}
```
