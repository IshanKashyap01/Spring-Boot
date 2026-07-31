# Bean Lifecycle

- Notice the last two attributes of the `<bean>` tag in the following example:

```xml
<bean id="example" class="io.example.Example" init-method="init" destroy-method="destroy"></bean>
```

- `init-method` takes a method of the class and calls it *right after the bean*
*is created*

- Meanwhile `destroy-method` calls its given method *just before the bean is*
*destroyed*

- `destroy-method` doesn't work for beans with `prototype` scope
