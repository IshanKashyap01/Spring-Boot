# Feign Client

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

- `@EnableFeignClients` add this over your main application class

```java
@FeignClient(name = "inventory-service")
public interface InventoryServiceClient
{
    @GetMapping("?code={code}&qty={quantity}")
    public boolean isInStock(@RequestParam Long code, @RequestParam Integer quantity);
}
```
