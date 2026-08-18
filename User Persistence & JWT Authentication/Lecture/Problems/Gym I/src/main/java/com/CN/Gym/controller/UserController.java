package com.CN.Gym.controller;


import com.CN.Gym.dto.UserRequest;
import com.CN.Gym.dto.WorkoutDto;
import com.CN.Gym.model.User;
import com.CN.Gym.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{
    private final UserService service;

    public UserController(UserService service)
    {
        this.service = service;
    }
    // 1. GET “/user/all”: This API allows the admin to fetch all the users from the database
    // and returns an OK HTTP status.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers()
    {
        return service.getAllUsers();
    }


    // 2. POST "/user/register" (@RequestBody UserRequest userRequest): This API registers a new user record into the database
    // and can be accessed by anyone. It returns a CREATED HTTP status.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public void registerUser(@RequestBody UserRequest dto)
    {
        service.createUser(dto);
    }


    // 3. GET “/user/{id}”: This API allows the customer to fetch the user record by its
    // ID and returns an OK HTTP status.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public User getUserById(@PathVariable Long id)
    {
        return service.getUserById(id);
    }


    // 4. PUT "/user/{id}": This API allows customers to update a user record by its ID and
    // returns an OK HTTP status.
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public void updateUser(@PathVariable Long id, @RequestBody UserRequest dto)
    {
        service.updateUser(dto, id);
    }


    // 5. DELETE "/user/{id}" (@PathVariable Long id): This API lets the admin delete a User record by its ID
    // and returns an OK HTTP status.
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id)
    {
        service.deleteUser(id);
    }


    // 6. POST "/user/workout/{userId}" (@RequestBody WorkoutDto workoutDto, @PathVariable Long userId): 
    // This API allows the trainer to assign a workout to a customer by its
    // ID and returns a CREATED HTTP status.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/workout/{userId}")
    @PreAuthorize("hasRole('TRAINER')")
    public void addWorkout(@RequestBody WorkoutDto dto, @PathVariable Long userId)
    {
        service.addWorkout(dto, userId);
    }
}