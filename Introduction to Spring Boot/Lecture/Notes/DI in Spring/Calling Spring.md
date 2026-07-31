# Calling Spring to Create the Object

- To leverage Spring's DI, first need to get the configuration we created
earlier

```java
public static Table getTable(String type)
{
    ApplicationContext context = new ClassPathXMLApplicationContext("applicationContext.xml");
    if(type.equalsIgnoreCase("long"))
    {
        return context.getBean("longTable");
    }
    else
    {
        return context.getBean("shortTable");
    }
}
```

- Using the `ApplicationContext` interface allows us to change where we get our
configuration later

- `ClassPathXMLApplicationContext` extends the above interface and deals with
application contexts written in XML

- Both of the above needs the following import in order to be used:

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXMLApplicationContext;
```

- `getBean()` inputs a bean ID and tells Spring to create a bean of said ID
