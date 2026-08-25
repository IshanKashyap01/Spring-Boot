# Filtering

```java
public List<Item> getWithFilters(Map<String, String> filters, String address)
{
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery query = builder.createQuery(Item.class);
    Root<Item> root = query.from(Item.class);
    List<Predicate> predicates = new ArrayList<>();
    for(String filter : filters.keySet())
    {
        predicates.add
        (
            builder.like(builder.lower(root.get(filter)), "%" + filter.toLowerCase() + "%")
        );
    }
    Join<Item, Address> join = root.join("item_details");
    predicate.add
    (
        builder.equal(builder.lower(address.get("state")), state.toLowerCase())
    );
    query.where(builder.and(predicates.toArray(new Predicate[0])));
    query.orderBy(builder.asc(item.get("name")));
    query.where(predicates);
    return manager.createQuery(query).getResultSet();
}
```
