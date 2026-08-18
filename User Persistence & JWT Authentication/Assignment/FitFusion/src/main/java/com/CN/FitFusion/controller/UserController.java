package com.CN.FitFusion.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.CN.FitFusion.dto.UserDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.service.DietService;
import com.CN.FitFusion.service.ExerciseService;
import com.CN.FitFusion.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController
{
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final DietService dietService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateUser(@RequestBody UserDto dto, @PathVariable Long id)
    {
        userService.updateUser(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
    }

    @GetMapping("/exercise/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<Exercise> getAllUserExercises(@PathVariable Long id)
    {
        return exerciseService.getAllUserExercises(id);
    }

    @GetMapping("/diet/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<Diet> getAllUserDiets(@PathVariable Long id)
    {
        return dietService.getAllUserDiets(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserDto dto)
    {
        userService.createUser(dto);
    }
}