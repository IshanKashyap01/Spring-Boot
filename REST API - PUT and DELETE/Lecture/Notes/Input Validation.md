# Input Validation in Spring

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
<dependency>
    <groupId>jakarta.validation</groupId>
    <artifactId>jakarta.validation-api</artifactId>
    <version>3.1.1</version>
</dependency>
```

```java
@NotBlank
@Size(min = 3, max = 20)
private String name;
@Min(1)
@Max(10)
private long rating;
```

```java
@PostMapping
public void createHotel(@Valid @RequestBody Hotel hotel)
{
    hotelService.createHotel(hotel);
}
```

- If you wish to handle validation errors in the same method, add a
`BindingResult` parameter right after the `@Valid` parameter

- You can check for errors with its `hasErrors()` method

- If validation fails, Spring throws a `MethodArgumentNotValidException`

- You should handle it once in a `@RestControllerAdvice` with an
`@ExceptionHandler`

- This encapsulates validation logic outside the controllers and into a
dedicated handler
