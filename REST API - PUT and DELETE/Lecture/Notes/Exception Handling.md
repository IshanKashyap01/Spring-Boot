# Exception Handling for API Errors

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException
{
    public HotelNotFoundException(String message)
    {
        super(message);
    }
}
```

- `@ResponseStatus` allows us to explicitly throw HTTP errors

```java
public Hotel get(int id)
{
    Hotel hotel = hotelRepository.getHotel(id);
    if(hotel == null)
    {
        throw new HotelNotFoundException("Hotel with id " + id + " not found");
    }
    return hotel;
}
```
