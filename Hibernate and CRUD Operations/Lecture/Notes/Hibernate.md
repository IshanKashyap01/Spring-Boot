# Hibernate

## Domain Layer

```java
@Entity
@Table(name = "student")
public class Student
{
    @Id @Column @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String standard;
}
```

- `@Entity` tells JPA this class is a persistent entity and its instances
should be managed by hibernate

- `@Table` tells JPA to map this entity to a database table with the specified
name

## Repository Layer

```java
@Repository
public class StudentRepository
{
    @Autowired
    EntityManager manager;

    public void get(int id)
    {
        manager.unwrap(Session.class).get(Student.class, id);
    }

    public void save(Student student)
    {
        Session session = manager.unwrap(Session.class);
        session.save(student);
    }
}
```

## Service Layer

```java
@Transactional
public void addStudent(Student student)
{
    studentRepository.save(student);
}
```
