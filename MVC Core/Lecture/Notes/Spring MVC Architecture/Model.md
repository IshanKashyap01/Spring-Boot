# Models in Spring MVC

```java
@Controller
public class SignUpController
{
    @RequestMapping("/signUp")
    public String getSignUpPage(Model model)
    {
        model.addAttribute("user", new Student());
        return "signup";
    }
}
```

- `Model` is injected by Spring into the code automatically and bound to the
view the function returns

- `addAttribute()` binds the `Student` object to the model

- Now the webpage can display this objects data or prompt the user to input
values specific to its properties

- When the client returns, Spring takes the returned data and creates a new
object of the same type
