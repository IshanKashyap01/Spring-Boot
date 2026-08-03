# Handling REST Template Errors

```java
public void deleteRating(String id)
{
    try
    {
        template.delete(URL + id);
    }
    catch(HttpClientErrorException e)
    {
        throw new HttpRatingNotFound("404");
    }
}
```

- `HttpClientErrorException` is thrown when any client errors (`4.x.x`) happens

- `HttpServerErrorException` is thrown when server errors (`5.x.x`) occur
