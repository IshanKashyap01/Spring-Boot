# Circuit Breaker

-If a downstream services crashes/slows down, incoming requests will back up

- They will eventually consume all threads on the server and lock the entire
app in waiting

- Circuit breakers immediately divert services to a fallback when requests
start backing up

## Hystrix

Dependency for hystrix (you'll also need actuator):

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    <version>2.2.10.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
    <version>2.2.10.RELEASE</version>
</dependency>
```

Enable hystrix in the application class:

```java
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class OrderApplication
```

Create fallback commands in the controller:

```java
@HystrixCommand(fallbackMethod = "fallbackPlaceOrder")
public String placeOrder(@RequestBody OrderRequest request)
{
    // implementation here...
}
// the arguments should be the same for the fallback to work
public String fallbackPlaceOrder(OrderRequest request)
{
    return "service not available";
}
```

Configure dashboard:

```yml
management:
  health:
    circuitbreakers:
      enabled: true
  endpoints:
    include: '*'
  endpoint:
    health:
      show-details: always
hystrix:
  dashboard:
    proxy-stream-allow-list: '*'
```
