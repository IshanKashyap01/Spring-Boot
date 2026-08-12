# Data Transfer Objects and Lombok

- DTOs decouple the JSON structure sent/received and the entity objects

- They allows us to only send what information is needed from the server and
hides entity details

- Lombok is a *java library* that replaces boilerplate like getters and setters
with annotations

- The replaced code is created at compile time and never clutters your code

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
class HotelDTO
{
    private String name;
    private String city;
    private String averageRating;
}
```

- `@Data` creates getters, setters, `toString()`, `hashCode()`, `equals()` and
adds constructors for all `final` or `@NonNull` fields

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.46</version>
    <scope>compile</scope>
</dependency>
```

- Before you can use lombok, you need to add it as a dependency to your
`pom.xml`
