# Bean Scope & Lifecycle

## Bean Scope

```java
@Component
@Scope("prototype")
public class Example
```

- `scope` annotation allows you to set the scope of your beans

- As `singleton` is the default scope, you don't need to mention it

## Bean Lifecycle

```java
@PostConstruct
public void init()
{
    // implementation here...
}
@PreDestroy
public void destroy()
{
    // implementation here...
}
```

- `PostConstruct` is the annotation equivalent of `init-method` in XML

- `PreDestroy` is the annotation equivalent of `destroy-method` in XML
