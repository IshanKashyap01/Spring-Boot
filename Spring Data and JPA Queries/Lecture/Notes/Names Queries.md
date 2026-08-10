# Named Queries

```java
@NamedQuery
(
    name = "Student.findBySection",
    query = "select s from Student where section = ?1"
)
@NamedNativeQuery
(
    name = "Student.findByCourse",
    query = "select s.name, c.course from student s inner join course c on s.course_id = c.id where c.id = ?1",
    resultClass = StudentCourse.class
)
public class Student
```

- They're declared over entity classes with a name for the function that calls
them

- They can  be used with variables (`:param`), you have to rely on arguments
instead

- For native queries, the class of the return type must be mentioned as well

```java
List<Student> findBySection(String section);

List<StudentCourse> findByCourse(int courseId);
```

- Then you only need to declare the functions in your repository
