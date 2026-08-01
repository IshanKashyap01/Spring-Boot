# Views in Spring MVC

```jsp
<!-- JSP's tag library for form element -->
<%@ taglib prefix="form" uri="https://www.springframework.org/tags/form" %>
<html>
    <form:form action="registerUser" modelAttribute="user">
        Name: <form:input path="name">
        <!-- Other attributes here... -->
    </form:form>
</html>
```

- `action` specifies the controller to send the form data once its submitted

- `modelAttribute` specifies the object the form is supposed to input data for

- `path` takes the property name that the input corresponds to

```java
// in the appropriate controller class...
@Autowired
UserService userService;

@RequestMapping("/registerUser")
public String registerUser(@ModelAttribute("user") Student student)
{
    return userService.register(student) ? "welcome" : "signup";
}
```

- `@ModelAttribute("user")` obtains the data returned from the client and
creates a `Student` object from it
