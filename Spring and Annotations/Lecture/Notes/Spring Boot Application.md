# `@SpringBootApplication` Annotation

- It is used to mark the main entry point class of a Spring Boot application

It is a combination of the following three, old annotations:

1. `@Configuration` marks the class as a config class for defining beans and
their dependencies

2. `@EnableAutoConfiguration` enables automatic configuration based on the
package, classpath and existing dependencies

3. `@ComponentScan` enables automatic detection and registration of
Spring-managed components

```java
ApplicationContext context = SpringApplication.run(MyApp.class, args);
```

- `SpringApplication.run()` tells Spring to start the the application using the
given class as the main config source

- It also returns an `ApplicationContext` that need not be closed
