# Dependency Injection

- To mark an dependency to get injected by Spring, we use `@Autowired`

```java
@Autowired
Instructor instructor
```

- The above code works as long as there is only one match for `Instructor`

- If there exists more, then you need to specify which implementation should be
used

```java
@Autowired
@Qualifier("springInstructor")
Instructor instructor
```

- However, it doesn't work with primitives
