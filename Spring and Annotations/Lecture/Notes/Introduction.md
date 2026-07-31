# Introduction to Spring Annotations

- As your application grows larger and more complex, so does your configuration
file

- That's why Spring Boot provides an alternative in **annotations**

```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.app");
```

- The above class is used to create contexts that read from Spring annotations
for application context

- The constructor takes the name of the package with annotations
