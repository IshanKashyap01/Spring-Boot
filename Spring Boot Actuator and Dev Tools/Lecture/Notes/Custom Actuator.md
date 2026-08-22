# Custom Actuator Implementation

```java
@Component
public class DatabaseHealthMonitor implements HealthIndicator
{
    private static final String DB_NAME = "social media db"
    @Override
    public Health health()
    {
        if(isDatabaseUp())
        {
            return Health.up().withDetails(DB_NAME, "is up and running").build();
        }
        return Health.down().withDetails(DB_NAME, "is down").build();
    }

    private boolean isDatabaseUp()
    {
        try
        {
            repository.findById(1);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
```

```java
@Endpoint(id="db")
@Component
public class DatabaseMonitoringEndpoint
{
    @ReadOperation
    public Health actuator()
    {
        return this.dbMonitor.health();
    }
}
```
