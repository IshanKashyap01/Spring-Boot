# Eureka Server and Client

## Eureka Server

```java
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication
```

- `@EnableEurekaServer` tells Spring that this is a Eureka server

- The following configuration tells Spring to let the clients register
themselves to the server

```yml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false
    fetch-registry: false
    serviceUrl:
      defaultZone: http://localhost:8761/eureka
server:
  port: 8761
```

## Eureka Client

- `@EnableEurekaClient` tells Spring that this is a Eureka client

- Add the below configuration to your client

```yml
spring:
  application:
    name: item-service
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
    register-with-eureka: true
    fetch-registry: true
```

- Also add a name to your application to show up on your Eureka dashboard

- Add a new config class to give you a rest template bean:

```java
@Configuration
public class CommunicationConfiguration
{
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
```

- Now you can use the name of the services instead of their ip address such as:

```java
private static final String RESOURCE = "http://inventory-service/";
```
