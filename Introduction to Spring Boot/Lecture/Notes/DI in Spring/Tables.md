# Tables

Because there can be any number of tables, we'll create an interface to
leverage polymorphism.

```java
public interface Table
{
    public String getDetails();
}
```

And we'll make two implementations for now.

```java
public class ShortTable implements Table
{
    @Override
    public String getDetails()
    {
        return "This is a short table with dimensions 4 x 2 ft";
    }
}
```

```java
public class LongTable implements Table
{
    @Override
    public String getDetails()
    {
        return "This is a long table with dimensions 6 x 4 ft";
    }
}
```
