# JWT Communication between Services

```java
@RestController
@RequestMapping("/hotel")
public class HotelController
{
    // other endpoints and fields...
    @GetMapping("/{id}")
    public Hotel getHotelById(@RequestHeader("Authorization") String token, @PathVariable Long id)
    {
        return service.getHotelById(token, id);
    }
}
```

```java
@Service
public class HotelService
{
    // other methods and fields...
    public Hotel getHotelById(String token, long id)
    {
        Hotel hotel = repository.findById(id);
        hotel.setRating(ratingService.getRating(id, token));
        return hotel;
    }
}
```

```java
@Service
public class RatingServiceClient
{
    // other methods and fields...
    public byte getRating(long id, String token)
    {
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", token);
        HttpEntity<Void> request = new HttpEntity<>(header);
        return template.exchange(url + id, HttpMethod.GET, request, Byte.class).getBody();
    }
}
```
