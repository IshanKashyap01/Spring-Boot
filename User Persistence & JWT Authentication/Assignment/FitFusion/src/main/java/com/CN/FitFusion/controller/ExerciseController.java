package com.CN.FitFusion.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.service.ExerciseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exercise")
public class ExerciseController
{
    private final ExerciseService service;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Exercise> getAllExercises()
    {
        return service.getAllExercises();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Exercise getExerciseById(@PathVariable Long id)
    {
        return service.getExerciseById(id);
    }

    @PostMapping("/create/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addExerciseToUser(@RequestBody ExerciseDto dto, @PathVariable Long userId)
    {
        service.addExerciseToUser(dto, userId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateExercise(@RequestBody ExerciseDto dto, @PathVariable Long id)
    {
        service.updateExercise(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExercise(@PathVariable Long id)
    {
        service.deleteExercise(id);
    }
}