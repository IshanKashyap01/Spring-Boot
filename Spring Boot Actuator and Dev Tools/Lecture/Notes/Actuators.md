# Spring Boot Actuator

- It provides production-ready features to monitor, manage and gather insights
about your app's health, performance and environment

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

- It provides built-in endpoints such as `/actuator`, `/health` and
`/health/{path}` by default

- Though it also delivers much more than that, which can be accessed as:

```yml
management:
  endpoints:
  web:
    # change the base path from `/actuator`
    # base-path: '/admin'
    exposure:
    # include all actuator endpoints
      include: '*'
  endpoint:
    health:
    # always show detailed analysis of the app's health
      show-details: always
  info:
    env:
      enabled: true
info:
  app:
    name: Social media connection service
    description: This app fetches all connections of a user
    version: 0.1.0-SNAPSHOT
```
