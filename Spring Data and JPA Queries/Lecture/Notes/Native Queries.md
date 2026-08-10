# Native Queries

- It's used in those rare circumstances when a query is truly complicated or
returns a massive result set

- For normal scenarios, JPQL is the better choice as it decouples the server
from the database

```java
@Query
(
    nativeQuery = true
    value = "select * from phones where name like %:name% limit 4",
)
List<Phone> getPhonesByNames(@Param("name") String name);
```
