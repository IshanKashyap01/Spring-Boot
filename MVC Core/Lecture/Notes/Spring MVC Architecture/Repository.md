# Repositories in Spring MVC

- A **Data Access Object** abstracts away all the details of opening db
connections, writing queries and mapping result sets

```java
public interface DataAccessObject<T>
{
    public Optional<T> get(int id);

    public int save(T t);
}
```

- `Optional` is an elegant way of working around the DB sending `null` values

```java
@Repository
public class StudentDataAccessObject implements DataAccessObject<Student>
{
    // how student object gets saved/deleted/accessed
}
```

- When you have more than one kind of object to store to the database, you
should create a DAO interface
