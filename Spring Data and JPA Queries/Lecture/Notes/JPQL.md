# Java Persistence Query Language

- It is a *database-agnostic* object-oriented query language

- Instead of targeting, database tables and columns, it targets Java objects
and their fields

```sql
select sd from StudentDetails sd where sd.city = 'indore' order by sd.name;
```

- Hibernate automatically translates it into native SQL at runtime

- To use custom JPQL queries, you only need to specify it in the interface
as follows:

```java
public interface StudentRepository extends JpaRepository<Student, Integer>
{
    @Query
    (
        "select s from Student s where s.section = ?1 order by s.standard, s.name"
    )
    List<Student> getAllStudentsFromSection(char section);

    @Query
    (
        "select s.standard, s.section, count(s) from Student s
        group by s.standard, s.section order by s.standard, s.section"
    )
    List<StudentCount> getNumberOfStudentsForEachSection();
}
```
