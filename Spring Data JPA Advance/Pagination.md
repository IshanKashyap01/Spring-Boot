# Pagination

- To paginate your records, you need to specify the page number, page size and
the sorting order

- You can input all three via *request parameters*

```java
public Page<Item> getItems(int offset, int pageSize, String field)
{
    Pageable page = PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, field));
    return repository.findAll(page);
}
```
