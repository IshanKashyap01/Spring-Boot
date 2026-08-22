package com.devtools.solution.controller;

import org.springframework.web.bind.annotation.*;
import com.devtools.solution.entity.User;
import com.devtools.solution.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController
{
    private final UserService service;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id)
    {
        return service.getUserById(id);
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody User user)
    {
        service.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id)
    {
        service.deleteUser(id);
    }
}