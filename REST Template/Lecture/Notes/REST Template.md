# REST Template

- It uses HTTP to *exchange data with third-party services* but not as
resources

```java
@Service
public class RatingServiceClient
{
    private final RestTemplate template;
    private static final String URL = "http://localhost:8081/ratings/";

    public RatingServiceClient(RestTemplateBuilder builder)
    {
        this.template = builder.build();
    }

    public byte getRating(String id)
    {
        ResponseEntity<Byte> response = template.getForEntity(this.URL + id, Byte.class);
        return response.getBody();
        // return template.getForObject(this.URL + id, Byte.class);
    }

    public void addRating(String id, byte rating)
    {
        template.postForObject(URL + id, rating, Void.class);
    }

    public void updateRating(String id, byte rating)
    {
        HttpEntity<Byte> request = new HttpEntity<>(rating);
        template.exchange(URL + id, HttpMethod.PUT, request, Void.class);
    }

    public void deleteRating(String id)
    {
        template.delete(URL + id)
    }
}
```

- `getForEntity()` returns the entire response: header, body, status, etc.

- `getForObject()` returns only the object in the response body

  - PUT, POST and DELETE also have an equivalent method with the same function

- `delete()` sends a `DELETE` request to the given API
