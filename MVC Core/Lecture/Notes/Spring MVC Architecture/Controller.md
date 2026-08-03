# Controllers in Spring MVC

```java
@Controller
public class HomeController
{
    @RequestMapping("/")
    public String getHomePage()
    {
        return "home";
    }
}
```

- `@Controller` marks a Java class as a web request handler

- `@RequestMapping` routes incoming web requests to specific controller classes
or handler methods

- You can set each `@RequestMapping` to only get triggered over specific
request

- The above code returns the name of the *view* to be responded with

- `ViewResolver` adds the prefix and suffix provided in the `Application.yml`
to create the path to this file
