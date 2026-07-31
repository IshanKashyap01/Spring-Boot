# Creating Beans

- To mark a class as a bean, you just have to annotate it with `@Component`

```java
import org.springframework.stereotype.Component;
@Component
public class MyBean
```

- You can add this annotation to an *abstract class* or an *interface* as long
as a sub class also has it

- You can also provide a bean id as follows:

```java
@Component("beanId")
```
