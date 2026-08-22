# Custom Metric

```java
@Configuration
@RequiredArgsConstructor
public class MetricConfiguration
{
    @Bean
    public MeterRegistry getMeterRegistry()
    {
        return new CompositeMeterRegistry();
    }
}
```

```java
@Service
public class ConnectionService
{
    private Counter counter = null;

    public ConnectionService(CompositeMeterRegistry registry)
    {
        counter = registry.counter("connection.call.counter");
    }

    public List<Connection> getConnections()
    {
        counter.increment();
        // method logic here...
    }
}
```
