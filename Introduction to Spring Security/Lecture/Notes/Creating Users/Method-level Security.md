# Method-level Security

```java
@PostMapping
@PreAuthorize("hasRole('ADMIN')")
public void createHotel(@RequestBody HotelDto dto)
{
    service.createHotel(dto);
}
```

- `@PreAuthorize` restricts access on a method level to specified roles

```java
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HotelSecurityConfiguration
```

- However, for it to work, you need to enable global method security first
