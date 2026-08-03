# REST with Spring Boot

```java
@RestController
@RequestMapping("/hotels")
public class HotelController
{
    @Autowired
    HotelService hotelService;

    @PostMapping
    public void addHotel(@RequestBody Hotel hotel)
    {
        this.hotelService.add(hotel);
    }
    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable int id)
    {
        return this.hotelService.get(id);
    }
    @GetMapping
    public List<Hotel> getAllHotels()
    {
        return this.hotelService.getAll();
    }
}
```

- `@RestController` is used by APIs to return raw data directly in the response

- `@RequestBody` creates a Java object from the data in the request body
