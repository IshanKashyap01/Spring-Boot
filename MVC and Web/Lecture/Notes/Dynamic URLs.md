# Creating Dynamic URLs in Spring Boot

```java
@RequestMapping("/registerUser")
public String userCreated(@ModelAttribute("user") User user)
{
    int userId = userService.registerUser(user);
    if(id != -1)
    {
        new ModelAndView("redirect:welcome?id=" + userId).getViewName();
    }
    return "sign-up";
}

@RequestMapping("/welcome")
public String userLoggedIn(@RequestParam("id") int userId, ModelMap map)
{
    User user = userService.getUser(userId);
    modelMap.addAttribute("user", user);
    return "welcome"
}
```

- `ModelAndView` bundles `Model` and `View` into a single object thus letting
you access both with one object

- Instead of query parameters, you can just do `welcome/" + userId` as well

- Then instead of `@RequestParam`, you'd use `@PathVariable`:

```java
@RequestMapping("/welcome/{id}")
public String userLoggedIn(@PathVariable int userId, Model model)
{
    map.addAttribute("userId", userId);
    return "redirect:profile/" + userService.getUser(userId).getName();
}
```

- The above code will redirect to the profile page and the url would look like
`.../profile/<username>`
