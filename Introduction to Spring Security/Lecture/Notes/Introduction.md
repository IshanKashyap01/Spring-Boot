# Introduction to Spring Security

- To use Spring security you first need to add it as a dependency of your
project

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

- When a request hits the server, it is first processed by Spring security

- *Authentication filter* intercepts the request and extracts and packages user
credentials

- *Authentication manager* receives the package and directs it to the correct
authentication provider

- By default, Spring security uses a form-based authentication

- That is, the client needs to enter credentials through a form before it can
access your API

- It also creates a default username and password
