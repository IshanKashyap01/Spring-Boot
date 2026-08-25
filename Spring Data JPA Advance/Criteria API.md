# Criteria API

- To create dynamic queries, we'll use JPA's built-in **Criteria API**

- It programmatically constructs database queries using Java objects instead of
JPQL

## Components of Criteria API

1. **Entity Manager**: JPA interface that provides DB connection

2. **Criteria Builder**: provided by entity manager to build the query

3. **Criteria Query**: object representing the query you're building

### Components of Criteria Query

1. **Root**: table on which the query is to be executed

2. **Predicate**: condition that evaluate to `true` or `false`; you can have
more than one

3. **Join**: entity to join your root with

## Architecture for Using Criteria API

- Keep your regular repository interface that extends `JpaRepository` as is

- Create a new interface with only the methods that need dynamic queries

- Create a concrete child class that implements those methods with criteria
builder

  - Mark this class as `@Repository`

- Make your regular repository extend this new interface as well

- Spring will create a proxy at runtime that'll build an implementation for:

    1. The JPA repository for JPA methods and,

    2. Use your custom implementation for your new interface

## Example

```java
public List<Item> findByName(String name)
{
    CriteriaBuilder builder = this.manager.getCriteriaBuilder();
    CriteriaQuery<Item> query = builder.createQuery(Item.class);
    Root<Item> item = query.from(Item.class);
    Predicate predicate = builder.like
    (
        builder.lower(item.get("name")),
        "%" + name.toLowerCase() + "%"
    );
    query.where(predicate);
    return this.manager.createQuery(query).getResultList();
}
```
