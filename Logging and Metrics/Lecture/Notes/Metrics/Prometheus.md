# Prometheus

- *Micrometer core* is included with actuators however, you'll have to add the
dependencies for *Prometheus*

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
    <scope>runtime</scope>
</dependency>
```

- Adding this dependency tells the actuator to also expose an endpoint for
Prometheus

- Now, you can configure Prometheus to scrape data from this endpoint
