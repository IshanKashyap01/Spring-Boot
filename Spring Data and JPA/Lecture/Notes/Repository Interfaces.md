# Repository Interfaces in Spring Boot JPA

```tree
Repository
    ↓
CrudRepository
    ↓
PagingAndSortingRepository
    ↓
JpaRepository
```

1. `Repository` is a marker interface i.e. it has no methods

2. `CrudRepository` adds CRUD functionality

3. `PagingAndSortingRepository` adds sorting and pagination (break large sets
of data into smaller, manageable chunks)

4. `JpaRepository` adds JPA specific functionality

## Change in Workflow

```java
public interface UserRepository<User, Integer> extends JpaRepository
```

- We only need to extend one of the above repositories and Spring Boot will
create an implementation at runtime

- Without Spring repositories:

```tree
Controller -> Service -> Interface -> Implementation -> DB
```

- With Spring repositories

```tree
Controller -> Service -> Interface extends <one of the above interfaces> -> DB
```
